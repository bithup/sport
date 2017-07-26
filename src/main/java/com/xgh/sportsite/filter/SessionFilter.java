package com.xgh.sportsite.filter;

import com.xgh.sportsite.services.ISysCacheService;
import com.xgh.spring.IocUtil;
import com.xgh.util.JSONUtil;
import com.xgh.util.PropertiesUtil;
import org.apache.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @project: 项目核心
 * @what:
 * @author:: duanxg
 * @update: 2014/10/13 17:03
 * @Email:
 */
public class SessionFilter extends OncePerRequestFilter {

	private Logger logger = Logger.getLogger(SessionFilter.class);

	protected ISysCacheService sysCacheService;

	protected List<String> filterList = new ArrayList<String>();

	public SessionFilter(){
		PropertiesUtil propertiesUtil = PropertiesUtil.getInstance(null);
		String urls = propertiesUtil.getValueByKey("filter.urls");
		String[] urlArray = urls.split(":");
		if (urlArray != null && urlArray.length > 0) {
			for (String url : urlArray) {
				filterList.add(url);
			}
		}
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取请求类型
		String requestType = request.getHeader("X-Requested-With");

		//此处准备过滤
		if (sysCacheService == null)
			sysCacheService = IocUtil.getBean("sysCacheService");
		//获取访问路径
		String requestUrl = request.getRequestURI();
		String contextPath = request.getContextPath();
		//去掉上下文路径
		if (null != contextPath) {
			requestUrl = requestUrl.substring(contextPath.length() + 1);
		}

		//获取basePath
		if (!contextPath.endsWith("/")) {
			contextPath = contextPath + "/";
		}
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath;

		//如果访问路径为/或空，重定向到首页
		if("/".equals(requestUrl) || "".equals(requestUrl)){
			response.sendRedirect(basePath+"home/baseInfo.htm");
			return;
		}
		//过滤配置文件config.properties中包含的路径
		if (filter(requestUrl) && !sysCacheService.isUserLogin(request)) {
			if("XMLHttpRequest".equals(requestType)) {
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("code", -2);
				resultMap.put("msg", "用户未登录");
				outJson(response, resultMap);
				return;
			}else{
				response.sendRedirect(basePath+"portal/loginInit.htm");
				return;
			}
		}
		//继续执行过滤器链
		filterChain.doFilter(request, response);

	}

	/**
	 * 对url进行对应的过滤，
	 * 如果filterSet中存在返回true
	 *
	 * @param url
	 * @return
	 */
	private boolean filter(String url) {
		if (filterList != null && !filterList.isEmpty()) {
			for (String _url : filterList) {
				if (url.startsWith(_url)) {
					return true;
				}
			}

			return false;
		} else
			return false;
	}

	protected void outJson(HttpServletResponse response, Object obj) {
		out(response, JSONUtil.getJson(obj));
	}

	/**
	 * 输出数据
	 *
	 * @param value
	 */
	protected void out(HttpServletResponse response, String value) {
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
			out.println(value);
		} catch (IOException e) {
			e.printStackTrace();
			logger.debug(e.getMessage(), e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
}
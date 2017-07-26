package com.xgh.sportsite.basic;


import com.xgh.sportsite.entity.MemberUser;
import com.xgh.sportsite.services.IMemberUserService;
import com.xgh.sportsite.services.ISysCacheService;
import com.xgh.sportsite.services.ISysUnitsService;
import com.xgh.sportsite.services.IZoneService;
import com.xgh.sportsite.util.ConstantUtil;
import com.xgh.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 基础Controller
 *
 * @author:段晓刚
 * @update：2015年11月6日 下午10:35:54
 * @Email：
 */
@Controller
public class BaseController {

    @Autowired
    protected ISysCacheService sysCacheService;

    @Autowired
    protected ISysUnitsService sysUnitsService;

    // 缓存
    @Autowired
    protected IMemberUserService memberUserService;

    @Autowired
    protected IZoneService zoneService;

    protected DecimalFormat decimalFormat = new DecimalFormat("0.00");
    protected HttpServletResponse response;
    protected HttpServletRequest request;
    protected HttpSession session;


    /**
     * @ModelAttribute放置在方法上面：表示请求该类的每个Action前都会首先执行它， 你可以将一些准备数据的操作放置在该方法里面
     */
    @ModelAttribute
    public void setReqAndResp(HttpServletResponse response, HttpServletRequest request) {
        this.response = response;
        this.request = request;
        this.session = request.getSession();
    }
    /*
    * 更新用户登录信息
    * */
    protected void updateCurrentMemberUser(HttpServletRequest request, MemberUser memberUser){
        sysCacheService.updateMemberUserCach(request,memberUser);
    }
    protected void outJson(Object obj) {
        out(JSONUtil.getJson(obj));
    }

    protected double formatDouble(double d){
        BigDecimal bigDecimal = BigDecimal.valueOf(d);
        return bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    /**
     * 输出数据
     *
     * @param value
     */
    protected void out(String value) {
        PrintWriter out = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            out.println(value);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    /**
     * 获取返回的resultMap
     *
     * @param flg
     * @param msg
     * @return
     */
    protected Map<String, Object> getResultMap(Object flg, Object msg) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        resultMap.put(com.xgh.sportsite.util.ConstantUtil.ResultKey.resultFlg.value(), flg);
        resultMap.put(com.xgh.sportsite.util.ConstantUtil.ResultKey.resultMsg.value(), msg);
        int a;
        return resultMap;
    }

    protected Map<String, Object> getResultMap(Object flg, Object msg, Object data) {
        Map<String, Object> resultMap = getResultMap(flg, msg);

        resultMap.put(com.xgh.sportsite.util.ConstantUtil.ResultKey.resultData.value(), data);

        return resultMap;
    }

    /**
     * 获取节点
     *
     * @return
     */
    protected int getOsNO() {
        return 101000;
    }

    protected String getSessionId() {
        return session.getId();
    }

    /**
     * 获取行业Id
     *
     * @return
     */
    protected long getCurrentInstId() {
        return 100L;
    }

    protected String getServerUrl() {
        return ConstantUtil.SERVER_URL;
    }

    protected void setZoneCode(String zoneCode) {
        //首先使用

        //Map<String,Object> userCacheMap = sysCacheService.getObject()
    }

    /**
     * 获取区域编码
     *
     * @return
     */
    protected String getZoneCode() {
        return "4101";
    }

    /**
     * 获取req请求参数
     * 我们对req参数有严格的规定，满足条件才可以放行
     * {
     * sLock:”配合skey使用进行请求有效性验证”,
     * sKey：””，
     * sId:”调用方随机生成id作为该条请求的唯一标识，参数返回时候原值返回”,
     * zoneCode:4101,
     * os:”系统类型，Android，IOS，wx，window,mac”,
     * osv:” Android4.4”,
     * appv:”app版本号”,
     * userKey:”确定用户是否有效”,
     * postData:
     * {
     * zoneCode:4101,
     * unitId:1001
     * 其他业务参数
     * }
     * }
     *
     * @return
     */
    protected Map<String, Object> getReqParams() {
        String _reqParams = request.getParameter("reqParams");

        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put(ConstantUtil.ReqKey.sLock.value(), request.getParameter(ConstantUtil.ReqKey.sLock.value()));
        reqMap.put(ConstantUtil.ReqKey.sKey.value(), request.getParameter(ConstantUtil.ReqKey.sKey.value()));
        reqMap.put(ConstantUtil.ReqKey.sId.value(), request.getParameter(ConstantUtil.ReqKey.sId.value()));
        reqMap.put(ConstantUtil.ReqKey.os.value(), request.getParameter(ConstantUtil.ReqKey.os.value()));
        reqMap.put(ConstantUtil.ReqKey.osv.value(), request.getParameter(ConstantUtil.ReqKey.osv.value()));
        reqMap.put(ConstantUtil.ReqKey.appv.value(), request.getParameter(ConstantUtil.ReqKey.appv.value()));
        reqMap.put(ConstantUtil.ReqKey.userKey.value(), request.getParameter(ConstantUtil.ReqKey.userKey.value()));
        String _postData = request.getParameter(ConstantUtil.ReqKey.postData.value());
        if (_postData.startsWith("{") && _postData.endsWith("}")) {
            reqMap.put(ConstantUtil.ReqKey.postData.value(), JSONUtil.getMap(_postData));
        }

        return reqMap;
    }

    /**
     * 获取req的业务数据
     *
     * @return
     */
    protected Map<String, Object> getReqPostParams() {
        Map<String, Object> reqMap = getReqParams();

        Map<String, Object> reqPostMap = new HashMap<String, Object>();

        if (!reqMap.isEmpty()) {
            Object _reqPost = reqMap.get(ConstantUtil.ReqKey.postData.value());
            if (_reqPost instanceof Map) {
                reqPostMap = (Map<String, Object>) _reqPost;
            }
        }

        return reqPostMap;
    }
}
package com.xgh.sportsite.controllers;

import com.xgh.sportsite.basic.BaseController;
import com.xgh.sportsite.entity.MemberUser;
import com.xgh.sportsite.entity.Recommend;
import com.xgh.sportsite.services.IRecommendService;
import com.xgh.sportsite.util.ConstantUtil;
import com.xgh.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaowenbo on 2016/3/28 0028.
 * 好创意 创意 2.挑毛病 3.吐槽
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/sportsite/recommend/")
public class RecommendController extends BaseController {
    @Autowired
    protected IRecommendService recommendService;
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
    }
    /**
     * 获取列表页面
     */
    @RequestMapping(value = "getRecommendList")
    public void getRecommendList() {
        Map<String, Object> postMap = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String kind = request.getParameter("kind");
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");
        postMap.put("page", Integer.parseInt(page));
        postMap.put("pageSize", Integer.parseInt(pageSize));
        if (StringUtil.notEmpty(kind) && StringUtil.isNumeric(kind)) {
            postMap.put("kind", Integer.parseInt(kind));
        } else {
            resultMap.put("resultFlag", 3);
            resultMap.put("resultMsg", "类型不正确");
            resultMap.put("resultData", null);
            outJson(resultMap);
            return;
        }
        //MemberUser memberUser = (MemberUser) request.getSession().getAttribute("memberUser");
        MemberUser memberUser=(MemberUser) sysCacheService.getMemberUserMap(request).get(ConstantUtil.SessionKeys.Member.value());
        if(memberUser!=null){
            postMap.put("memberId",memberUser.getId());
        }else{
            postMap.put("memberId","");
        }
        List<Map<String, Object>> list =
                this.recommendService.getRecommendList(postMap);
        if (list != null && list.size() > 0) {
            resultMap.put("resultFlag", 1);
            resultMap.put("resultMsg", "获取列表成功");
            resultMap.put("resultData", list);
        } else {
            resultMap.put("resultFlag", 0);
            resultMap.put("resultMsg", "暂无数据");
            resultMap.put("resultData", null);
        }
        outJson(resultMap);
    }

    /**
     * 添加操作
     */
    @RequestMapping(value = "addRecommend")
    public void addRecommend(@ModelAttribute Recommend recommend) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (recommend != null) {
            //MemberUser memberUser = (MemberUser) request.getSession().getAttribute("memberUser");
            MemberUser memberUser=(MemberUser) sysCacheService.getMemberUserMap(request).get(ConstantUtil.SessionKeys.Member.value());
            recommend.setMemberId(memberUser.getId());
            //校验参数开始====================start
            if (StringUtil.isEmpty(recommend.getContext())) {
                resultMap.put("resultFlag", 3);
                resultMap.put("resultMsg", "内容不能为空");
                outJson(resultMap);
            }
            if (recommend.getKind() <= 0) {
                resultMap.put("resultFlag", 3);
                resultMap.put("resultMsg", "类型不正确");
                outJson(resultMap);
            }
     /*       if (StringUtil.isEmpty(recommend.getMemberid())) {
                resultMap.put("resultFlag", 3);
                resultMap.put("resultMsg", "用户id不正确");
                outJson(resultMap);
            }*/
            //校验参数结束====================end
            int flag = 0;
            flag =this.recommendService.add(recommend);
            if (flag == 1) {
                resultMap.put("resultFlag", 1);
                resultMap.put("resultMsg", "添加成功");
            } else {
                resultMap.put("resultFlag", 0);
                resultMap.put("resultMsg", "添加失败");
            }
            } else {
            resultMap.put("resultFlag", 3);
            resultMap.put("resultMsg", "参数名不正确，请参考接口文档");
            }
            outJson(resultMap);
            }

    @RequestMapping(value = "/init")
    public ModelAndView init() {
        String  kind=request.getParameter("kind");
        ModelAndView view = new ModelAndView();
        //MemberUser memberUser1= (MemberUser) request.getSession().getAttribute("memberUser");
        MemberUser memberUser=(MemberUser) sysCacheService.getMemberUserMap(request).get(ConstantUtil.SessionKeys.Member.value());
//        if(memberUser==null)
//        {
//            view.setViewName("member/loginInit");
//            return view;
//        }
        view.addObject("memid", memberUser.getId()) ;
        if (kind.equals("1")) {
            view.setViewName("bottom/idea");
        } else if(kind.equals("2"))
        {
            view.setViewName("bottom/wrong");
        } else if(kind.equals("3"))
        {
            view.setViewName("bottom/teasing");
        }
        return view;
    }


}

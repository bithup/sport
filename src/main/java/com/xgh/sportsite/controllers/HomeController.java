package com.xgh.sportsite.controllers;

import com.xgh.sportsite.basic.BaseController;
import com.xgh.sportsite.entity.*;
import com.xgh.sportsite.services.*;
import com.xgh.sportsite.util.ConstantUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/3/20.
 */
@Controller
@Scope("prototype")
@RequestMapping("/home/")
public class HomeController extends BaseController {
    private Logger logger=Logger.getLogger(HomeController.class);

    @Autowired
    protected ISubjectService subjectService;

    @Autowired
    protected IChildVanueService childVanueService;

    @Autowired
    protected ICoachService coachService;

    @Autowired
    protected IActivityService activityService;

    @Autowired
    protected IKindsService kindsService;

    @RequestMapping("baseInfo")
    public ModelAndView baseInfo(){
        ModelAndView view=new ModelAndView();
        String type_ = request.getParameter("type_");
        view.setViewName("home/baseInfo");
        view.addObject("type_",type_);
        return view;
    }

    @RequestMapping("index")
    public ModelAndView index(){
        ModelAndView view=new ModelAndView();
        String type_ = request.getParameter("type_");
        view.setViewName("home/index");
        //轮播图
        Map<String,Object> map = new HashMap<String, Object>();
        List<Subject> list = subjectService.getList(map);
        for(Subject subject:list){
            subject.setRelativePath(ConstantUtil.SERVER_URL+subject.getMobileFilePath());
        }
        view.addObject("list",list);
        //首页运动类型
        List<Kinds> kindsList = kindsService.getIndexKinds();
        view.addObject("kindsList",kindsList);
        String zoneName = request.getParameter("zoneName");
        if (zoneName==null||zoneName.equals("")){
            zoneName="郑州市";
        }
        map.put("zoneName",zoneName);
        //热门场馆
        List<Map<String,Object>> venueList = childVanueService.getRecommendVenue(request);
        view.addObject("venueList",venueList);
        //热门教练

        List<Map<String,Object>> coachList = coachService.getRecommendCoach(map);
        view.addObject("coachList",coachList);
        //热门活动
        map.put("isRecommend", "1");
        List<Map<String, Object>> activityList = activityService.getRecommendActivity(map);
        view.addObject("activityList",activityList);
        view.addObject("type_",type_);
        view.addObject("zoneName",zoneName);
        return view;
    }
}

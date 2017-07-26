package com.xgh.sportsite.controllers;

import com.xgh.sportsite.basic.BaseController;
import com.xgh.sportsite.services.IChildVanueService;
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
 * Created by Administrator on 2016/12/14.
 */
@Controller
@Scope("prototype")
@RequestMapping("/index/")
public class IndexController extends BaseController {

    private Logger logger = Logger.getLogger(IndexController.class);

    @Autowired
    protected IChildVanueService childVanueService;

    @RequestMapping(value="getIndexSearch")
    public void getIndexSearch(){

        Map<String,Object> resultMap = new HashMap<String, Object>();
        Map<String,Object> map = new HashMap<String, Object>();
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        String indexCondition = request.getParameter("indexCondition");
        map.put("page",Integer.parseInt(page));
        map.put("pagesize",Integer.parseInt(pagesize));
        map.put("indexCondition",indexCondition);
        List<Map<String,Object>> list = childVanueService.getIndexSearch(map);
        if(list!=null&&list.size()>0){
            resultMap = getResultMap("1","首页搜索成功！",list);
        }else{
            resultMap = getResultMap("0","首页搜索失败！");
        }
        outJson(resultMap);

    }
    //首页跳转到搜索页面
    @RequestMapping(value = "indexSearchInit")
    public ModelAndView indexSearchInit(){
        ModelAndView view=new ModelAndView();
        view.setViewName("search/indexSearchInit");
        return view;
    }

    //搜索结果展示页面
    @RequestMapping(value = "indexSearch")
    public ModelAndView indexSearch(){
        ModelAndView view=new ModelAndView();
        view.setViewName("search/indexSearch");
        String indexCondition=request.getParameter("indexCondition");
        view.addObject("indexCondition",indexCondition);

        return view;
    }

}

package com.xgh.sportsite.controllers;

import com.xgh.sportsite.basic.BaseController;
import com.xgh.sportsite.entity.Kinds;
import com.xgh.sportsite.entity.Zone;
import com.xgh.sportsite.services.IKindsService;
import com.xgh.sportsite.services.IZoneService;
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
@RequestMapping("/kinds/")
public class KindsController extends BaseController{

    private Logger logger = Logger.getLogger(IndexController.class);

    @Autowired
    protected IKindsService kindsService;

    @Autowired
    protected IZoneService zoneService;

    /**
     * 首页运动类型相关教练、场馆、活动信息
     */
    @RequestMapping(value="kindIndex")
    public ModelAndView getIndexKinds(){
        ModelAndView view=new ModelAndView();
        view.setViewName("kinds/kind_index");
        //商品类别 1：场馆，2：教练，3：活动
        String goodsType=request.getParameter("goodsType");
        //查询条件
        String searchCondition=request.getParameter("searchCondition");
        if(searchCondition==null){
            //防止页面js出错而不执行
            searchCondition="1";
        }
        //运动类型id
        String id=request.getParameter("id");
        //运动名称
        String name=request.getParameter("name");
        String condition=request.getParameter("condition");
        view.addObject("searchCondition",searchCondition);
        view.addObject("goodsType",goodsType);
        view.addObject("condition",condition);
        view.addObject("sportId",id);
        view.addObject("sportName",name);
        return view;
    }


    /**
     * 首页其他运动类型
     */
    @RequestMapping(value="otherKinds")
    public ModelAndView getIndexOtherKinds(){
        ModelAndView view=new ModelAndView();
        view.setViewName("kinds/otherKinds");
        String goodsType=request.getParameter("goodsType");
        //区别从约教练、约场馆、约好友页面和首页进入
        String flg=request.getParameter("flg");
        if(flg!=null&&flg!=""){
            view.addObject("flg",flg);
        }
        List<Map<String,Object>> list = kindsService.getIndexOtherKinds();
        if(list!=null&&list.size()>0){
            //将参数传入JSP
            view.addObject("list",list);
            view.addObject("goodsType",goodsType);
        }else{
            //获取失败处理逻辑
        }
        return view;

    }


    @RequestMapping(value="getHeadKinds")
    public void getHeadKinds(){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        List<Kinds> list = kindsService.getHeadKinds();
        if(list!=null&&list.size()>0){
            resultMap = getResultMap("1","获取头部运动类型成功！",list);
        }else{
            resultMap = getResultMap("0","暂无数据！");
        }
        outJson(resultMap);

    }

    /*
    * 跳转到场馆排序方式页面
    * */
    @RequestMapping(value = "index_fp")
    public ModelAndView index_fp(){
        ModelAndView view=new ModelAndView();
        view.setViewName("kinds/index_fp");
        String sportId=request.getParameter("sportId");
        String sportName=request.getParameter("sportName");

        view.addObject("sportId",sportId);
        view.addObject("sportName",sportName);

        return view;

    }

    /*
    * 跳转到选择教练性别页面
    * */
    @RequestMapping(value = "trainer_xb")
    public ModelAndView trainer_xb(){
        ModelAndView view=new ModelAndView();
        view.setViewName("kinds/trainer_xb");
        String sportId=request.getParameter("sportId");
        String sportName=request.getParameter("sportName");

        view.addObject("sportId",sportId);
        view.addObject("sportName",sportName);
        return view;
    }

    /*
    * 跳转到选择活动收费方式页面
    * */
    @RequestMapping(value = "activity_px")
    public ModelAndView activity_px(){
        ModelAndView view=new ModelAndView();
        view.setViewName("kinds/activity_px");
        String sportId=request.getParameter("sportId");
        String sportName=request.getParameter("sportName");

        view.addObject("sportId",sportId);
        view.addObject("sportName",sportName);
        return view;
    }

    /*
    * 跳转到选择区页面
    * */
    @RequestMapping(value = "index_zone")
    public ModelAndView index_zone(){
        ModelAndView view=new ModelAndView();
        view.setViewName("kinds/index_zone");
        //城市名称
        String zoneName=request.getParameter("cityName");
        String goodsType=request.getParameter("goodsType");
        String sportId=request.getParameter("sportId");
        String sportName=request.getParameter("sportName");

        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map map = new HashMap();
        map.put("zoneName", zoneName);
        List<Zone> zoneList = zoneService.getZones(map);
        Zone zone = new Zone();
        if (zoneList != null && zoneList.size() > 0) {
            zone = zoneList.get(0);
        }
        String zoneCode = zone.getCode();
        map.put("pcode", zoneCode);

        List<Zone> zoneNameList = zoneService.getAreaInfoByPcode(map);

        if (zoneList != null && zoneList.size() > 0) {
            Zone zone1 = new Zone();
            zone1.setId(zone.getId());
            zone1.setName("全部");
            zone1.setCode("");
            zoneNameList.add(0, zone1);
        }
        view.addObject("zoneNameList",zoneNameList);
        view.addObject("goodsType",goodsType);
        view.addObject("sportId",sportId);
        view.addObject("sportName",sportName);
        return view;

    }


    @RequestMapping(value="getChildVenueKinds")
    public void getChildVenueKinds(){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        List<Map<String,Object>> list = kindsService.getIndexOtherKinds();
        if(list!=null&&list.size()>0){
            resultMap = getResultMap("1","获取其他运动类型成功！",list);
        }else{
            resultMap = getResultMap("0","获取其他运动类型失败！");
        }
        outJson(resultMap);

    }
}

package com.xgh.sportsite.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.xgh.sportsite.basic.BaseController;
import com.xgh.sportsite.entity.*;
import com.xgh.sportsite.services.*;
import com.xgh.sportsite.util.ConstantUtil;
import com.xgh.sportsite.util.DateToWeek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.xgh.sportsite.util.ConstantUtil.DictClumn.id;

/**
 * Created by CQ on 2016/12/12.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/activity")
public class ActivityController extends BaseController {


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
    }


    @Autowired
    protected IActivityService activityService;

    @Autowired
    protected IOrderService orderService;

    @Autowired
    protected IKindsService kindsService;

    @Autowired
    protected IFileDataService fileDataService;

    @Autowired
    protected IHouseService houseService;

    @Autowired
    protected IRemarkService remarkService;



    /**
     * 根据活动id查询活动详情
     */
    @RequestMapping(value = "activityDetail")
    public ModelAndView getActivityDetail() {
        ModelAndView view=new ModelAndView();
        view.setViewName("activity/activityDetail");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String activityId = request.getParameter("activityId");
        String type_=request.getParameter("type_");
        String status_=request.getParameter("status_");
        Activity activity = activityService.get(Long.parseLong(activityId));
        Kinds kinds = kindsService.get(activity.getSportId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dataId", activity.getId());
        map.put("dataCode", "active");//教练
        map.put("dataVersion", 0);
        map.put("server", ConstantUtil.SERVER_URL);
        map.put("dataType", 1);
        List<Map<String, Object>> fileDataList = fileDataService.getFileDatas(map);//教练上传的多张图片

        //判断是否登录，如果已经登录判断用户是否收藏该商品
        int flag=0;//0:未收藏，1：已收藏
        int isLogin=0;
        if(sysCacheService.isUserLogin(request)){
            MemberUser memberUser=(MemberUser) sysCacheService.getMemberUserMap(request).get(ConstantUtil.SessionKeys.Member.value());
            view.addObject("memberId",memberUser.getId());
            isLogin=1;

            long memberId = memberUser.getId();
            long dataId = Long.parseLong(activityId);
            int type = 2;
            Map<String,Object> m = new HashMap<String, Object>();
            m.put("memberId",memberId);
            m.put("dataId",dataId);
            m.put("type",type);
            List<House> houses = houseService.checkHouseExist(m);
            if(houses!=null&&houses.size()==1){
                House house1 = houses.get(0);
                if(house1.getStatus()==0){
                    flag=0;
                }else if(house1.getStatus()==1){
                    flag=1;
                }
            }else if(houses!=null&&houses.size()==0){
                flag=0;
            }else{
                view.setViewName("error/error");
            }
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("dataId", id);
        params.put("type","0");
        List<Remark> remarks = remarkService.getByDataId(params);//getByDataId为添加的方法
        //将评价信息放入remarkList
        List<String> remarkList = new ArrayList<String>();
        int good = 0;
        int middle = 0;
        int bad = 0;
        if(remarks.size()>0){
            for(Remark remark:remarks){
                if(remark.getKind()==0){
                    good += 1;
                }else if(remark.getKind()==1){
                    middle += 1;
                }else if(remark.getKind()==2){
                    bad += 1;
                }
            }
            DecimalFormat df_ = new DecimalFormat("##%");
            int total = good+middle+bad;
            String good_ = df_.format((good*1.0)/(total*1.0));
            String middle_ = df_.format((middle*1.0)/(total*1.0));
            String bad_ = df_.format((bad*1.0)/(total*1.0));
            remarkList.add("好评："+good_);
            remarkList.add("中评："+middle_);
            remarkList.add("差评："+bad_);
        }
        params.put("dataId", id);
        params.put("dataCode", ConstantUtil.FileUploadCode.ChildVenue.value());
        params.put("dataVersion", 0);
        params.put("dataType",1);
        params.put("server", ConstantUtil.SERVER_URL);

        SimpleDateFormat df   =   new   SimpleDateFormat("yyyy:MM:dd");
        SimpleDateFormat df2   =   new   SimpleDateFormat("HH:mm:ss");
        Calendar rightNow   =   Calendar.getInstance();
        DateToWeek dateToWeek = new DateToWeek();
        List<Map<String,Object>> weekList= new ArrayList<Map<String,Object>>();
        try {
            for(int i = 0;i<7;i++){
                Map<String,Object> dateMap = new HashMap<String, Object>();
                if(i>0){
                    rightNow.add(Calendar.DAY_OF_MONTH,+1);
                }
                String rightDate = df.format(rightNow.getTime());
                String date = df2.format(rightNow.getTime());
                String week = dateToWeek.dayForWeek(rightDate);
                dateMap.put("date",date);
                dateMap.put("rightDate",rightDate);
                if(i==0){
                    dateMap.put("week","今天");
                }else{
                    dateMap.put("week",week);
                }
                weekList.add(dateMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //获取活动的报名人数/销量
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("goodsId",activity.getId());
        paramMap.put("orderType",2);
        int enrollCount = orderService.getAcountByGoodsId(paramMap);

        if (activity != null) {
            view.addObject("activity",activity);
            view.addObject("fileData", fileDataList);
            view.addObject("kinds",kinds);
            view.addObject("isLogin",isLogin);
            view.addObject("flag",flag);
            //添加remarkList，可查询评价信息
            view.addObject("remarkList",remarkList);
            view.addObject("type_",type_);
            view.addObject("status_",status_);
            view.addObject("enrollCount",enrollCount);
        }else{
            view.setViewName("error/error");
        }

//        outJson(resultMap);
        return view;
    }

    /**
     * 根据运动类型查询活动列表
     */
    /*
    * 根据运动类型查询活动初始化页面
    *
    * */
    @RequestMapping("activityListInit")
    public ModelAndView activityListInit(){
        ModelAndView view=new ModelAndView();
        view.setViewName("activity/activityListInit");
        List<Kinds> headKindsList = kindsService.getHeadKinds();
        view.addObject("headKindsList",headKindsList);
        String sportId=request.getParameter("id");
        view.addObject("sportId",sportId);
        return view;
    }

    @RequestMapping(value = "getActivityBySportId")
    public void getActivityBySportId() {

        //Map<String, Object> resultMap = new HashMap<String, Object>();
        String sportId = request.getParameter("sportId");
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sportId", sportId);
        map.put("page",Integer.parseInt(page));
        map.put("pageSize",Integer.parseInt(pageSize));
        List<Map<String, Object>> activityList = activityService.getActivityBySportId(map);

//        if (activityList != null && activityList.size() > 0) {
//            resultMap = getResultMap("1", "活动列表查询成功!", activityList);
//        } else {
//            resultMap = getResultMap("0", "活动列表查询失败!");
//        }
        outJson(activityList);

    }

    /**
     * 添加活动
     */
    @RequestMapping(value = "addActivity")
    public void addActivity(HttpServletRequest request, @ModelAttribute Activity activity) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (activity != null) {
            int aom = activity.getId() > 1 ? 1 : 0;
            int flag = activityService.save(request, activity);
            if (flag == 1) {
                if (aom == 0) {
                    resultMap = getResultMap("1", "添加成功!");
                } else
                    resultMap = getResultMap("1", "修改成功!");
            } else {
                if (aom == 0) {
                    resultMap = getResultMap("0", "添加失败!");
                } else
                    resultMap = getResultMap("0", "修改失败!");
            }
            outJson(resultMap);
        }
    }

    //约好友跳转到搜索页面
    @RequestMapping(value = "activitySearchInit")
    public ModelAndView activitySearchInit(){
        ModelAndView view=new ModelAndView();
        view.setViewName("activity/activitySearchInit");
        return view;
    }

    /**
     * 根据活动名称查询活动(模糊查询)
     */
    @RequestMapping(value = "getActivityIndexResearch")
    public void getActivityIndexResearch() {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        String indexCondition = request.getParameter("indexCondition");//查询条件
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("indexCondition", indexCondition);
        map.put("page", Integer.parseInt(page));
        map.put("pageSize", Integer.parseInt(pageSize));
        List<Map<String, Object>> activityList = activityService.getActivityIndexResearch(map);

        if (activityList != null && activityList.size() > 0) {
            resultMap = getResultMap("1", "查询成功!", activityList);
        } else {
            resultMap = getResultMap("0", "查询失败!");
        }
        outJson(resultMap);

    }

    /**
     * 查询热门活动
     */
    @RequestMapping(value = "getRecommendActivity")
    public void getRecommendActivity() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        Map<String, Object> map = new HashMap<String, Object>();
        String zoneName = request.getParameter("zoneName");
        map.put("isRecommend", "1");
        map.put("zoneName",zoneName);
        List<Map<String, Object>> activityList = activityService.getRecommendActivity(map);
        if (activityList != null && activityList.size() > 0) {
            resultMap = getResultMap("1", "热门活动列表显示成功", activityList);
        } else {
            resultMap = getResultMap("0", "热门活动列表显示失败");
        }
        outJson(resultMap);
    }

    /**
     * 生成活动订单
     *
     * @param order
     */
    @RequestMapping(value = "createActiveOrder")
    public void createActiveOrder(@ModelAttribute Order order) {

        outJson(orderService.createActiveOrder(request, order));
    }

    /**
     * 获取会员发布的活动列表
     */
    @RequestMapping(value = "getPublishActivity")
    public void getPublishActivity() {
        Map<String, Object> map = new HashMap<String, Object>();
        MemberUser memberUser=(MemberUser) sysCacheService.getMemberUserMap(request).get(ConstantUtil.SessionKeys.Member.value());
        request.setAttribute("memberId",memberUser.getId());
        outJson(activityService.getPublishActivity(request, map));
    }

    /**
     * 报名详情
     */
    @RequestMapping(value = "getActivitySignDetail")
    public void getActivitySignDetail() {

        outJson(activityService.getActivitySignDetail(request));
    }

    /**
     * 会员取消已经发布的活动
     */
    @RequestMapping(value = "cancelActivity")
    public void cancelActivity() {

        outJson(activityService.cancel(request));
    }




    /**
     * 根据区域搜活动
     */
    @RequestMapping(value="getActivityByZoneId")
    public void getActivityByZoneId(){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        Map<String,Object> map = new HashMap<String, Object>();
        String kindsId = request.getParameter("kindsId");
        String zoneId = request.getParameter("zoneId");
        if(null!=zoneId&&!"".equals(zoneId)){
            Zone zone = zoneService.get(Long.parseLong(zoneId));
            if(zone.getLevel().equals("4")){
                map.put("isAll","1");
            }
        }
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pageSize");
        map.put("sportId",kindsId);
        map.put("zoneId",zoneId);
        map.put("page",Integer.parseInt(page));
        map.put("pagesize",Integer.parseInt(pagesize));
        List<Map<String,Object>> list = activityService.getListPage(map);
//        if(list!=null&&list.size()>0){
//            resultMap = getResultMap("1","获取活动成功！",list);
//        }else{
//            resultMap = getResultMap("0","获取活动失败！");
//        }
        outJson(list);

    }


    /**
     * 根据区域搜活动
     */
    @RequestMapping(value="getActivityByIsFree")
    public void getActivityByIsFree(){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        Map<String,Object> map = new HashMap<String, Object>();
        String kindsId = request.getParameter("kindsId");
        String isFree = request.getParameter("isFree");
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pageSize");
        map.put("sportId",kindsId);
        map.put("isFree",isFree);
        map.put("page",Integer.parseInt(page));
        map.put("pagesize",Integer.parseInt(pagesize));
        List<Map<String,Object>> list = activityService.getListPage(map);
//        if(list!=null&&list.size()>0){
//            resultMap = getResultMap("1","获取活动成功！",list);
//        }else{
//            resultMap = getResultMap("0","获取活动失败！");
//        }
        outJson(list);

    }

    /*
    * 跳转到确认活动页面
    * */
    @RequestMapping(value = "confirm_hd")
    public ModelAndView confirm_hd(){
        ModelAndView view=new ModelAndView();
        view.setViewName("activity/confirm_hd");
        String activityId = request.getParameter("activityId");
        Activity activity = activityService.get(Long.parseLong(activityId));
        Kinds kinds = kindsService.get(activity.getSportId());
        MemberUser memberUser=(MemberUser) sysCacheService.getMemberUserMap(request).get(ConstantUtil.SessionKeys.Member.value());
        view.addObject("activity",activity);
        view.addObject("kinds",kinds);
        view.addObject("memberId",memberUser.getId());
        return view;
    }

    /*
    * 添加活动
    * */
    @RequestMapping(value = "build_act")
    public ModelAndView buil_act(){
        ModelAndView view=new ModelAndView();
        view.setViewName("activity/build_act");
        return view;
    }


}

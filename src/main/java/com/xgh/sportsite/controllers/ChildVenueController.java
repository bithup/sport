package com.xgh.sportsite.controllers;

import com.xgh.sportsite.basic.BaseController;
import com.xgh.sportsite.entity.*;
import com.xgh.sportsite.services.*;
import com.xgh.sportsite.util.ConstantUtil;
import com.xgh.sportsite.util.DateToWeek;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2016/12/8.
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/venue/")
public class ChildVenueController extends BaseController{
    private Logger logger = Logger.getLogger(MemberUserController.class);
    //获取约教练、约场馆头部运动类型
    @Autowired
    protected IKindsService kindsService;

    @Autowired
    protected IChildVanueService childVanueService;

    @Autowired
    protected IFileDataService fileDataService;

    @Autowired
    protected IHouseService houseService;

    @Autowired
    protected IRemarkService remarkService;

    @Autowired
    protected IVenueService venueService;

    /**
     * 约场馆
     */
    /*
    * 约场馆页面跳转
    * */
    @RequestMapping(value = "venueListInit")
    public ModelAndView venueListInit(){
        ModelAndView view=new ModelAndView();
        view.setViewName("venue/venueListInit");
        List<Kinds> headKindsList = kindsService.getHeadKinds();
        String sportId=request.getParameter("id");
        view.addObject("sportId",sportId);
        view.addObject("headKindsList",headKindsList);
        return view;
    }
    @RequestMapping(value="getVenueBySportId")
    public void getListVenue(){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        Map<String,Object> map = new HashMap<String, Object>();
        String kindsId = request.getParameter("kindsId");
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pageSize");

        if(null!=kindsId&&!"".equals(kindsId)){
            map.put("sportId",kindsId);
        }
        map.put("page",Integer.parseInt(page));
        map.put("pagesize",Integer.parseInt(pagesize));
        List<Map<String,Object>> list = childVanueService.getListPage(map);
//        if(list.size()>0){
//            resultMap = getResultMap("1","获取场馆列表成功！",list);
//        }else{
//            resultMap = getResultMap("0","获取场馆列表失败！");
//        }
        outJson(list);
    }

    /**
     * 获取场馆详情
     */
    @RequestMapping(value = "venueDetail")
    public ModelAndView venueDetail(){
        ModelAndView view=new ModelAndView();
        view.setViewName("venue/venueDetail");
        String id = request.getParameter("id");
        String type_ = request.getParameter("type_");
        String status_=request.getParameter("status_");
        /* Map<String,Object> resultMap = new HashMap<String, Object>();
        Map<String,Object> outMap = new HashMap<String, Object>();*/
        //判断是否登录，如果已经登录判断用户是否收藏该商品
        int flag=0;//0:未收藏，1：已收藏
        int isLogin=0;
        if(sysCacheService.isUserLogin(request)){
            MemberUser memberUser=(MemberUser) sysCacheService.getMemberUserMap(request).get(ConstantUtil.SessionKeys.Member.value());
            view.addObject("memberId",memberUser.getId());
            isLogin=1;
            //Map<String,Object> resultMap = new HashMap<String, Object>();
            long memberId = memberUser.getId();
            long dataId = Long.parseLong(id);
            int type = 0;
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
        Map<String, Object> venueDetail = childVanueService.getDetail(Long.parseLong(id));

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
        List<Map<String, Object>> fileDataList = fileDataService.getFileDatas(params);

        SimpleDateFormat df   =   new   SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df2   =   new   SimpleDateFormat("MM-dd");
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
                 /* outMap.put("venueDetail",venueDetail);
                 outMap.put("fileDataList",fileDataList);
                 outMap.put("weekList",weekList);*/
        if(venueDetail!=null){
            //resultMap = getResultMap("1","获取场馆详情成功！",outMap);
            view.addObject("venueDetail",venueDetail);
            view.addObject("fileDataList",fileDataList);
            view.addObject("weekList",weekList);
            view.addObject("isLogin",isLogin);
            view.addObject("flag",flag);
            //添加remarkList，可查询评价信息
            view.addObject("remarkList",remarkList);
            view.addObject("type_",type_);
            view.addObject("status_",status_);
        }else{
            view.setViewName("error/error");
        }
        //outJson(resultMap);
        return view;
    }

    /**
     * 获取热门场馆
     */
    @RequestMapping(value="getRecommendVenue")
    public void getRecommendVenue(){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        List<Map<String,Object>> list = childVanueService.getRecommendVenue(request);
        if(list.size()>0){
            resultMap = getResultMap("1","获取热门场馆成功！",list);
        }else{
            resultMap = getResultMap("0","获取热门场馆失败！");
        }
        outJson(resultMap);
    }
    /*
    * 场馆排序方式页面跳转
    * */

    @RequestMapping("indexfp")
    public ModelAndView indexfp(){
        ModelAndView view=new ModelAndView();
        view.setViewName("venue/index_fp");
        String sportId=request.getParameter("sportId");
        view.addObject("sportId",sportId);
        return view;
    }

    /**
     * 根据区域查场馆
     */
    @RequestMapping(value="getVenueByZone")
    public void getVenueByZone(){
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
        String pagesize = request.getParameter("pagesize");
        map.put("sportId",kindsId);
        map.put("zoneId",zoneId);
        map.put("page",Integer.parseInt(page));
        map.put("pagesize",Integer.parseInt(pagesize));
        List<Map<String,Object>> list = childVanueService.getListPage(map);
//        if(list!=null&&list.size()>0){
//            resultMap = getResultMap("1","获取场馆成功！",list);
//        }else{
//            resultMap = getResultMap("0","获取场馆失败！");
//        }
        outJson(list);
    }


    /**
     * 场馆按价格排序
     */
    @RequestMapping(value="getVenueByPrice")
    public void getVenueByPrice(){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        Map<String,Object> map = new HashMap<String, Object>();
        String kindsId = request.getParameter("kindsId");
        String isPrice = "1";
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        map.put("sportId",kindsId);
        map.put("isPrice",isPrice);
        map.put("page",Integer.parseInt(page));
        map.put("pagesize",Integer.parseInt(pagesize));
        List<Map<String,Object>> list = childVanueService.getListPage(map);
//        if(list!=null&&list.size()>0){
//            resultMap = getResultMap("1","获取场馆成功！",list);
//        }else{
//            resultMap = getResultMap("0","获取场馆失败！");
//        }
        outJson(list);

    }

    /**
     * 场馆按大小排序
     */
    @RequestMapping(value = "getVenueBySize")
    public void getVenueBySize(){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        Map<String,Object> map = new HashMap<String, Object>();
        String kindsId = request.getParameter("kindsId");
        String isSize = "1";
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        map.put("sportId",kindsId);
        map.put("isSize",isSize);
        map.put("page",Integer.parseInt(page));
        map.put("pagesize",Integer.parseInt(pagesize));
        List<Map<String,Object>> list = childVanueService.getListPage(map);
//        if(list!=null&&list.size()>0){
//            resultMap = getResultMap("1","获取场馆成功！",list);
//        }else {
//            resultMap = getResultMap("0","获取场馆失败！");
//        }
        outJson(list);
    }


    //约场馆跳转到搜索页面
    @RequestMapping(value = "venueSearchInit")
    public ModelAndView venueSearchInit(){
        ModelAndView view=new ModelAndView();
        view.setViewName("venue/venueSearchInit");
        return view;
    }


    /***
     * 约场馆搜索
     */
    @RequestMapping(value="getListVenueSearch")
    public void getListVenueSearch(){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        Map<String,Object> map = new HashMap<String, Object>();
        String venueName = request.getParameter("venueName");
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        map.put("venueName",venueName);
        map.put("page",Integer.parseInt(page));
        map.put("pagesize",Integer.parseInt(pagesize));
        List<Map<String,Object>> list = childVanueService.getListPage(map);
        if(list!=null&&list.size()>0){
            resultMap = getResultMap("1","获取场馆成功！",list);
        }else {
            resultMap = getResultMap("0","获取场馆失败！");
        }
        outJson(resultMap);
    }

    /*
    * 确认场馆页面
    * */
    @RequestMapping(value = "confirm_cg")
    public ModelAndView confirm_cg(){
        ModelAndView view=new ModelAndView();
        view.setViewName("venue/confirm_cg");
        String cuttings=request.getParameter("cuttings");
        String selectDate=request.getParameter("selectDate");
        String totalPrice=request.getParameter("totalPrice");
        String week=request.getParameter("week");
        String cut=request.getParameter("cut");
        String[] cutting = cut.split(";|；");
        List<Map<String,Object>> cutList=new ArrayList<Map<String,Object>>();
        for(int i=0;i<cutting.length;i++){
        Map<String,Object> m=new HashMap<String,Object>();
            String[] s=cutting[i].split(":|：");
            m.put("no",s[0]);
            int et=Integer.parseInt(s[1])+1;
            String tp=s[1]+":00-"+et+":00";
            m.put("tp",tp);
            cutList.add(m);
        }
        String kindsName=request.getParameter("kindsName");
        String address=request.getParameter("address");
        MemberUser memberUser=(MemberUser) sysCacheService.getMemberUserMap(request).get(ConstantUtil.SessionKeys.Member.value());
        view.addObject("memberId",memberUser.getId());
        view.addObject("kindsName",kindsName);
        char[] k=kindsName.toCharArray();

        view.addObject("kn",k[0]);
        view.addObject("address",address);
        view.addObject("cuttings",cuttings);
        view.addObject("selectDate",selectDate);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        try {
            date=sdf.parse(selectDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        view.addObject("totalPrice",totalPrice);
        view.addObject("cutList",cutList);
        view.addObject("week",week);
        view.addObject("date",date);
        return view;
    }

    /*
    * 跳转主场馆信息页面
    * */
    @RequestMapping(value = "mySite")
    public ModelAndView mySite(){
        ModelAndView view=new ModelAndView();
        view.setViewName("venue/mySite");
        String memberId=request.getParameter("id");
        Map<String, Object> map = new HashMap<String, Object>();
        MemberUser memberUser1 = memberUserService.get(Long.parseLong(memberId));
        Long venueId = memberUser1.getData4();
        view.addObject("venueId",venueId);
        Venue venue=venueService.get(venueId);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("dataId", venueId);
        params.put("dataCode", ConstantUtil.FileUploadCode.Venue.value());
        params.put("dataVersion", 0);
        params.put("dataType",1);
        params.put("server", ConstantUtil.SERVER_URL);
        List<Map<String, Object>> fileDataList = fileDataService.getFileDatas(params);
        int lengh = fileDataList.size();
        view.addObject("fileDataList",fileDataList);
        view.addObject("lengh",lengh);
        view.addObject("venue",venue);
        long Zone=venue.getZoneId();
        if(Zone!=0){
            Zone zone=zoneService.get(Zone);
            String zoneName=zone.getName();
            view.addObject("zoneName",zoneName);
            String pcode=zone.getPcode();
            view.addObject("pcode",pcode);
        }
        view.addObject("memberId",memberId);
        return view;
    }
    /*
    * 跳转我的场馆列表页面
    * */
    @RequestMapping(value = "otherSite")
    public ModelAndView otherSite(){
        ModelAndView view=new ModelAndView();
        view.setViewName("venue/otherSite");
        String memberId=request.getParameter("id");
        Map<String, Object> map = new HashMap<String, Object>();
        MemberUser memberUser1 = memberUserService.get(Long.parseLong(memberId));
        Long venueId = memberUser1.getData4();
        map.put("venueId",venueId);
        List<Map<String,Object>> childVanueList= childVanueService.getChildVenue(map);
        if(childVanueList!=null&&childVanueList.size()>0){
            Object childVanueId=childVanueList.get(0);
            view.addObject("childVanueId",childVanueId);
        }
        view.addObject("memberId",memberId);
        view.addObject("venueId",venueId);
        return view;
    }

    /**
     * 获取分场馆列表
     */
    @RequestMapping(value="getChildVenueList")
    public void getChildVenueList(HttpServletRequest request){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        Map<String,Object> map = new HashMap<String, Object>();
        String venueId = request.getParameter("venueId");
        map.put("venueId",venueId);
        List<Map<String,Object>> childVanueMap = childVanueService.getChildVenue(map);
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        if (childVanueMap!=null&&childVanueMap.size()>0){
            for (Map<String,Object> childVanueMap_:childVanueMap){
                Map<String,Object> map1 = new HashMap<String, Object>();
                map1.put("id",childVanueMap_.get("id"));
                map1.put("childVenueName",childVanueMap_.get("childVenueName"));
                map1.put("kindsName",childVanueMap_.get("kindsName"));
                map1.put("venueName",childVanueMap_.get("venueName"));
                map1.put("pictureUrl",ConstantUtil.SERVER_URL+childVanueMap_.get("pictureUrl"));
                map1.put("price",childVanueMap_.get("price"));
                map1.put("salesPrice",childVanueMap_.get("salesPrice"));
                if(String.valueOf(childVanueMap_.get("isRecommend"))!=null&&!"".equals(String.valueOf(childVanueMap_.get("isRecommend")))){
                    if(Integer.parseInt(String.valueOf(childVanueMap_.get("isRecommend")))==0){
                        map1.put("isRecommend","不是");
                    }else if(Integer.parseInt(String.valueOf(childVanueMap_.get("isRecommend")))==1){
                        map1.put("isRecommend","是");
                    }
                }
                mapList.add(map1);
            }
            resultMap = getResultMap("1","获取分场馆列表成功",mapList);
        }else {
            resultMap = getResultMap("0","获取分场馆列表失败");
        }
        outJson(resultMap);
    }
    /*
 * 我的场馆(分场馆)列表点击添加跳转页面
 * */
    @RequestMapping(value = "addVenue")
    public ModelAndView addVenue(){
        ModelAndView view=new ModelAndView();
        view.setViewName("venue/addVenue");
        String memberId=request.getParameter("id");
        String flag=request.getParameter("flag");
        String childVanueId = request.getParameter("childVanueId");
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> params = new HashMap<String, Object>();
        MemberUser memberUser1 = memberUserService.get(Long.parseLong(memberId));
        Long venueId = memberUser1.getData4();

        map.put("childVanueId",childVanueId);
        view.addObject("memberId",memberId);
        view.addObject("childVanueId",childVanueId);
        params.put("dataId", childVanueId);
        params.put("dataCode", ConstantUtil.FileUploadCode.ChildVenue.value());
        params.put("dataVersion", 0);
        params.put("dataType",1);
        params.put("server", ConstantUtil.SERVER_URL);
        List<Map<String, Object>> fileDataList = fileDataService.getFileDatas(params);
        int lengh = fileDataList.size();
        view.addObject("fileDataList",fileDataList);
        view.addObject("lengh",lengh);
        if(childVanueId!=null&&!childVanueId.equals("")){
            ChildVenue childVenue = childVanueService.get(Long.parseLong(childVanueId));
            long sportId=childVenue.getSportId();
            Kinds kinds=kindsService.get(sportId);
            String kindName=kinds.getName();
            view.addObject("kindName",kindName);
            if(!flag.equals("add")){
                view.addObject("childVenue", childVenue);
           /* if (childVanueMap!=null&&childVanueMap.size()>0) {
                Map<String, Object> map1 = new HashMap<String, Object>();
                Map<String, Object> childVanueMap_ = childVanueMap.get(0);
                map1.put("sportId", childVanueMap_.get("sportId"));
                map1.put("kindsName", childVanueMap_.get("kindsName"));
                view.addObject("kindsName", childVanueMap_.get("kindsName"));
            }*/
            }
        }
        view.addObject("memberId",memberId);
        view.addObject("venueId",venueId);
        return view;
    }


    /**
     * 获取分场馆详情
     */
    @RequestMapping(value="getChildVenue")
    public void getChildVenue(HttpServletRequest request){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        Map<String,Object> map = new HashMap<String, Object>();
        String childVenueId = request.getParameter("childVenueId");
        map.put("childVenueId",childVenueId);
        List<Map<String,Object>> childVanueMap = childVanueService.getChildVenue(map);
        if (childVanueMap!=null&&childVanueMap.size()>0){
            Map<String,Object> map1 = new HashMap<String, Object>();
            Map<String,Object> childVanueMap_ = childVanueMap.get(0);
            map1.put("childVenueName",childVanueMap_.get("childVenueName"));
            map1.put("sportId",childVanueMap_.get("sportId"));
            map1.put("kindsName",childVanueMap_.get("kindsName"));
            map1.put("venueNo",childVanueMap_.get("venueNo"));
            map1.put("venueId",childVanueMap_.get("parentId"));
            map1.put("venueName",childVanueMap_.get("venueName"));
            map1.put("pictureUrl",ConstantUtil.SERVER_URL+childVanueMap_.get("pictureUrl"));
            map1.put("price",childVanueMap_.get("price"));
            map1.put("salesPrice",childVanueMap_.get("salesPrice"));
            map1.put("unit",childVanueMap_.get("unit"));
            map1.put("serviceInfo",childVanueMap_.get("serviceInfo"));
            map1.put("tips",childVanueMap_.get("tips"));
            map1.put("ord",childVanueMap_.get("ord"));
            map1.put("facility",childVanueMap_.get("facility"));
            if(String.valueOf(childVanueMap_.get("isRecommend"))!=null&&!"".equals(String.valueOf(childVanueMap_.get("isRecommend")))){
                if(Integer.parseInt(String.valueOf(childVanueMap_.get("isRecommend")))==0){
                    map1.put("isRecommend","不是");
                }else if(Integer.parseInt(String.valueOf(childVanueMap_.get("isRecommend")))==1){
                    map1.put("isRecommend","是");
                }
            }
            map1.put("capacity",childVanueMap_.get("capacity"));
            //获取分场馆图片
            HashMap hashMap = new HashMap();
            hashMap.put("dataType", 1);
            hashMap.put("dataId", childVenueId);
            hashMap.put("dataCode",ConstantUtil.FileUploadCode.ChildVenue.value());
            hashMap.put("dataVersion", 0);
            hashMap.put("server", ConstantUtil.SERVER_URL);
            List<FileData> fileDataList = fileDataService.getList(hashMap);
            List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
            if (fileDataList!=null&&fileDataList.size()>0){
                for (FileData fileDataList_:fileDataList){
                    HashMap map2 = new HashMap();
                    String venueUrl = ConstantUtil.SERVER_URL+fileDataList_.getRelativePath()+fileDataList_.getFileName();
                    map2.put("venueUrl",venueUrl);
                    map2.put("id",fileDataList_.getId());
                    mapList.add(map2);
                }
            }
            map1.put("venueUrlList",mapList);
            resultMap = getResultMap("1","获取分场馆详情成功",map1);
        }else {
            resultMap = getResultMap("0","获取分场馆详情失败");
        }
        outJson(resultMap);
    }

    /**
     * 编辑/发布分场馆
     */
    @RequestMapping(value="savaChildVenue")
    public void updateChildVenue(HttpServletRequest request, @ModelAttribute ChildVenue childVenue){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        String id=request.getParameter("id");
        if(id.equals("0")||id==null||id.equals("")){//发布
            int flag = childVanueService.addChildVenue(request,childVenue);
            if (flag>0){
                resultMap = getResultMap("1","发布分场馆成功");
            }else {
                resultMap = getResultMap("0","发布分场馆失败");
            }
        }else {//编辑
            int flag = childVanueService.updateChildVenue(request, childVenue);
            if (flag > 0) {
                resultMap = getResultMap("1", "编辑分场馆成功");
            } else if (flag == -1) {
                resultMap = getResultMap("-1", "获取分场馆失败");
            } else {
                resultMap = getResultMap("0", "编辑分场馆失败");
            }
        }
        outJson(resultMap);
    }

    /**
     * 删除分场馆
     */
    @RequestMapping(value="deleteChildVenue")
    public void deleteChildVenue(HttpServletRequest request){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        int flag = childVanueService.deleteChildVenue(request);
        if (flag>0){
            resultMap = getResultMap("1","删除分场馆成功");
        }else if (flag==-1){
            resultMap = getResultMap("-1","获取分场馆失败");
        }else {
            resultMap = getResultMap("0","删除分场馆失败");
        }
        outJson(resultMap);
    }
    /**
     * 编辑主场馆
     */
    @RequestMapping(value="updateVenue")
    public void updateVenue(HttpServletRequest request,@ModelAttribute Venue venue){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        int flag = venueService.updateVenue(request,venue);
        if (flag>0){
            resultMap = getResultMap("1","编辑主场馆成功");
        }else if (flag==-1){
            resultMap = getResultMap("-1","获取主场馆失败");
        }else {
            resultMap = getResultMap("0","编辑主场馆失败");
        }
        outJson(resultMap);
    }

}

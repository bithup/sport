package com.xgh.sportsite.controllers;

import com.xgh.sportsite.basic.BaseController;
import com.xgh.sportsite.entity.*;
import com.xgh.sportsite.services.*;
import com.xgh.sportsite.util.ConstantUtil;
import com.xgh.sportsite.util.DateToWeek;
import com.xgh.util.DateUtil;
import com.xgh.util.FileUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.xgh.sportsite.util.ConstantUtil.DictClumn.id;

/**
 * Created by CQ on 2016/12/8.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/coach/")
public class CoachController extends BaseController {
    private Logger logger = Logger.getLogger(CoachController.class);

    @Autowired
    protected ICoachService coachService;

    @Autowired
    protected ICoachCourseService coachCourseService;

    @Autowired
    protected IFileDataService fileDataService;

    @Autowired
    protected IOrderService orderService;

    //获取约教练、约场馆头部运动类型
    @Autowired
    protected IKindsService kindsService;

    @Autowired
    protected IHouseService houseService;

    @Autowired
    protected IRemarkService remarkService;


    /*跳转教练信息页面*/
    @RequestMapping("coachInformation")
    public ModelAndView coachInformation() {
        ModelAndView view = new ModelAndView();
        view.setViewName("coach/coachInformation");
        MemberUser memberUser=(MemberUser) sysCacheService.getMemberUserMap(request).get(ConstantUtil.SessionKeys.Member.value());
        MemberUser memberUser1 = memberUserService.get(memberUser.getId());
        Long coachId = memberUser1.getData4();
        Map<String, Object> coach = coachService.get(coachId);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("dataId", coachId);
        params.put("dataCode", "coach");
        params.put("dataVersion", 0);
        params.put("dataType",1);
        params.put("server", ConstantUtil.SERVER_URL);
        List<Map<String, Object>> fileDataList = fileDataService.getFileDatas(params);
        int lengh = fileDataList.size();
        view.addObject("fileDataList",fileDataList);
        view.addObject("lengh",lengh);
        Object height_ = coach.get("height");
        if (height_!=null){
            double height=Double.parseDouble(height_.toString());
            int a = (int) height;
            coach.put("height", a);
        }
        Object weight_ = coach.get("weight");
        if (weight_!=null){
            double weight =Double.parseDouble(weight_.toString());
            int b = (int) weight;
            coach.put("weight", b);
        }
        if(coach.get("idCardFront")!=null){

        coach.put("idCardFront",ConstantUtil.SERVER_URL+coach.get("idCardFront"));
        }
        if(coach.get("idCardBack")!=null){

        coach.put("idCardBack",ConstantUtil.SERVER_URL+coach.get("idCardBack"));
        }
        view.addObject("coach", coach);

        if(coach.get("sportId")!=null){
            Long sportId=(Long)coach.get("sportId");
            if(!sportId.equals(new Long(0))){
                Kinds kinds=kindsService.get(sportId);
                view.addObject("sportName",kinds.getName());
            }

        }

        return view;
    }
    /**
     * 教练完善信息
     * @param coach
     *
     */
    @Transactional
    @RequestMapping(value="updateCoachInfo")
    public void updateCoachInfo(@ModelAttribute Coach coach,String birthday0){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date birthday= null;
        try {
            birthday = sdf.parse(birthday0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        coach.setBirthday(birthday);
        Map<String,Object> resultMap = new HashMap<String, Object>();
        coach.setUpdateDate(new Date());

        //根据用户id查询教练信息
        Coach coach1 = (Coach) coachService.getCoachByCoachId(coach.getId());
        coach.setId(coach1.getId());
        coach.setCreateDate(coach1.getCreateDate());
        coach.setTelPhone(coach1.getTelPhone());
        coach.setStatus(1);
        coach.setIsCheck(coach1.getIsCheck());
        coach.setIsRecommend(coach1.getIsRecommend());
        String da=request.getParameter("data");
        if(!da.equals("")){
            Long zoneId=Long.parseLong(da);
            Zone zone = zoneService.get(zoneId);
            coach.setData1(zone.getCode());
            coach.setData6(zoneId);
        }
        coach.setData4(coach1.getData4());
        coach.setData5(coach1.getData5());

        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator fileNames = multipartRequest.getFileNames();//可以上传一张也可以上传多张图片

            for (int i = 0; fileNames.hasNext(); ++i) {
                String name = (String) fileNames.next();

                MultipartFile myfile = multipartRequest.getFile(name);
                if (myfile.isEmpty()) {
                    logger.info("文件未上传");
                    if(null!=name&&!"".equals(name)&&name.equals("idCardFrontFile")){
                        coach.setIdCardFront(coach1.getIdCardFront());
                    }
                    if(null!=name&&!"".equals(name)&&name.equals("idCardBackFile")){
                        coach.setIdCardBack(coach1.getIdCardBack());
                    }
                    if(null!=name&&!"".equals(name)&&name.equals("logoFile")){
                        coach.setHeadPath(coach1.getHeadPath());
                        coach.setHeadRealPath(coach1.getHeadRealPath());
                    }
                } else {//文件不为空
                    String OriginalFileName = myfile.getOriginalFilename();
                    String saveName = DateUtil.getSystemTime().getTime() + "" + i + OriginalFileName.substring(OriginalFileName.lastIndexOf("."), OriginalFileName.length());
                    SimpleDateFormat formatdate = new SimpleDateFormat("yyyy/MM/dd/");
                    Date date = new Date();
                    String relative_path = formatdate.format(date);
                    String serverPath = ConstantUtil.SERVER_URL;
                    String realPath = ConstantUtil.SAVE_PATH  + "coach" + "/" + relative_path;
                    File filePath = new File(realPath);
                    if (!filePath.exists()) {
                        filePath.mkdirs();
                    }
                    FileUtil.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, saveName));

                    FileData fileData = new FileData();
                    fileData.setDataId(coach1.getId());
                    fileData.setNid(0);
                    fileData.setInstId(1);
                    fileData.setInstNid(1010001);
                    fileData.setInstCode("sport");
                    fileData.setUnitId(2);
                    fileData.setUnitNid(0);
                    fileData.setUnitCode("sport");
                    fileData.setDataCode("coach");
                    fileData.setDataVersion(0);
                    fileData.setPath(ConstantUtil.SAVE_PATH);
                    fileData.setRelativePath("/" + "coach" + "/" + relative_path);
                    fileData.setFileName(saveName);
                    fileData.setOldName(OriginalFileName);
                    fileData.setFileSize(myfile.getSize());
                    fileData.setFileSuffix(OriginalFileName.substring(OriginalFileName.lastIndexOf("."), OriginalFileName.length()));
                    fileData.setStatus(1);
                    fileData.setCreateDate(date);
                    fileData.setOrd(2);

                    if(name.equals("idCardFrontFile")){//身份证正面照
                        coach.setIdCardFront("/" + "coach" + "/" + relative_path + saveName);
                        fileData.setType(1);
                        fileData.setDataType(0);

                        Map<String,Object> map = new HashMap<String, Object>();
                        map.put("dataId", coach1.getId());
                        map.put("dataCode", "coach");//教练
                        map.put("dataVersion", 0);
                        map.put("server", ConstantUtil.SERVER_URL);
                        map.put("dataType", 0);
                        map.put("type","1");
                        map.put("ord","2");
                        List<Map<String, Object>> fileDataList = fileDataService.getFileDatas(map);//教练上传的身份证正面照
                        if(fileDataList.size()>0){
                            Map<String,Object> fileData1 =  fileDataList.get(0);
                            fileData.setId(Long.parseLong(fileData1.get("id").toString()));
                            fileDataService.update(fileData);
                        }else{
                            fileDataService.add(fileData);
                        }

                    } else if(null!=name&&!"".equals(name)&&name.equals("idCardBackFile")){//身份证背面
                        coach.setIdCardBack("/" + "coach" + "/" + relative_path + saveName);
                        fileData.setType(3);
                        fileData.setDataType(0);

                        Map<String,Object> map = new HashMap<String, Object>();
                        map.put("dataId", coach1.getId());
                        map.put("dataCode", "coach");//教练
                        map.put("dataVersion", 0);
                        map.put("server", ConstantUtil.SERVER_URL);
                        map.put("dataType", 0);
                        map.put("type","3");
                        map.put("ord","2");
                        List<Map<String, Object>> fileDataList = fileDataService.getFileDatas(map);//教练上传的身份证背面
                        if(fileDataList.size()>0){
                            Map<String,Object> fileData1 =  fileDataList.get(0);
                            fileData.setId(Long.parseLong(fileData1.get("id").toString()));
                            fileDataService.update(fileData);
                        }else{
                            fileDataService.add(fileData);
                        }

                    } else if(null!=name&&!"".equals(name)&&name.equals("logoFile")){//教练头像
                        coach.setHeadPath("/" + "coach" + "/" + relative_path + saveName);
                        coach.setHeadRealPath(serverPath + "/" + "coach" + "/" + relative_path + saveName);
                        fileData.setType(2);
                        fileData.setDataType(0);

                        Map<String,Object> map = new HashMap<String, Object>();
                        map.put("dataId", coach1.getId());
                        map.put("dataCode", "coach");//教练
                        map.put("dataVersion", 0);
                        map.put("server", ConstantUtil.SERVER_URL);
                        map.put("dataType", 0);
                        map.put("type","2");
                        map.put("ord","2");
                        List<Map<String, Object>> fileDataList = fileDataService.getFileDatas(map);//教练上传的头像
                        if(fileDataList.size()>0){
                            Map<String,Object> fileData1 =  fileDataList.get(0);
                            fileData.setId(Long.parseLong(fileData1.get("id").toString()));
                            fileDataService.update(fileData);
                        }else{
                            fileDataService.add(fileData);
                        }
                    } else{//实景图片
                        fileData.setDataType(1);
                        fileData.setType(0);
                        fileDataService.add(fileData);
                    }
                }

            }
        } catch (IOException var18) {
            var18.printStackTrace();
            var18.printStackTrace();
            logger.error(var18.getMessage(), var18);
        }

        int flag = coachService.update(coach);
        if(flag>0){
            resultMap = getResultMap("1","修改成功");
        }else{
            resultMap = getResultMap("0","修改失败，请重试");
        }
        outJson(resultMap);
    }
    /**
     * 获取教练详情
     */

    @RequestMapping(value = "coachDetail")
    public ModelAndView getCoachDetailInfo() {
        ModelAndView view = new ModelAndView();
        view.setViewName("coach/coachDetail");
        String coachId = request.getParameter("coachId");
        String type_=request.getParameter("type_");
        String status_=request.getParameter("status_");
        //获取教练信息
        Map<String, Object> coach = coachService.get(Long.parseLong(coachId));
            Object height_ = coach.get("height");
            double height=Double.parseDouble(height_.toString());
            int a = (int) height;
            coach.put("height", a);
            Object weight_ = coach.get("weight");
            double weight =Double.parseDouble(weight_.toString());
            int b = (int) weight;
            coach.put("weight", b);
        //获取教练上传的文件
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dataId", coachId);
        map.put("dataCode", "coach");//教练
        map.put("dataVersion", 0);
        map.put("server", ConstantUtil.SERVER_URL);
        map.put("dataType", 1);
        List<Map<String, Object>> fileDataList = fileDataService.getFileDatas(map);//教练上传的多张图片
        //获取教练的课程
        Map<String, Object> map_ = new HashMap<String, Object>();
        map_.put("coachId", coachId);
        List<Map<String, Object>> courseList = coachCourseService.getCourseListByMemId(map_);

        //判断是否登录，如果已经登录判断用户是否收藏该商品
        int flag = 0;//0:未收藏，1：已收藏
        int isLogin = 0;
        if (sysCacheService.isUserLogin(request)) {
            MemberUser memberUser = (MemberUser) sysCacheService.getMemberUserMap(request).get(ConstantUtil.SessionKeys.Member.value());
            view.addObject("memberId", memberUser.getId());
            isLogin = 1;
            Map<String, Object> resultMap = new HashMap<String, Object>();
            long memberId = memberUser.getId();
            long dataId = Long.parseLong(coachId);
            int type = 1;
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("memberId", memberId);
            m.put("dataId", dataId);
            m.put("type", type);
            List<House> houses = houseService.checkHouseExist(m);
            if (houses != null && houses.size() == 1) {
                House house1 = houses.get(0);
                if (house1.getStatus() == 0) {
                    flag = 0;
                } else if (house1.getStatus() == 1) {
                    flag = 1;
                }
            } else if (houses != null && houses.size() == 0) {
                flag = 0;
            } else {
                view.setViewName("error/error");
            }
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("dataId", id);
        params.put("type", "0");
        List<Remark> remarks = remarkService.getByDataId(params);//getByDataId为添加的方法
        //将评价信息放入remarkList
        List<String> remarkList = new ArrayList<String>();
        int good = 0;
        int middle = 0;
        int bad = 0;
        if (remarks.size() > 0) {
            for (Remark remark : remarks) {
                if (remark.getKind() == 0) {
                    good += 1;
                } else if (remark.getKind() == 1) {
                    middle += 1;
                } else if (remark.getKind() == 2) {
                    bad += 1;
                }
            }
            DecimalFormat df_ = new DecimalFormat("##%");
            int total = good + middle + bad;
            String good_ = df_.format((good * 1.0) / (total * 1.0));
            String middle_ = df_.format((middle * 1.0) / (total * 1.0));
            String bad_ = df_.format((bad * 1.0) / (total * 1.0));
            remarkList.add("好评：" + good_);
            remarkList.add("中评：" + middle_);
            remarkList.add("差评：" + bad_);
        }
        params.put("dataId", id);
        params.put("dataCode", ConstantUtil.FileUploadCode.ChildVenue.value());
        params.put("dataVersion", 0);
        params.put("dataType", 1);
        params.put("server", ConstantUtil.SERVER_URL);

        SimpleDateFormat df   =   new   SimpleDateFormat("yyyy:MM:dd");
        SimpleDateFormat df2   =   new   SimpleDateFormat("HH:mm:ss");
        Calendar rightNow = Calendar.getInstance();
        DateToWeek dateToWeek = new DateToWeek();
        List<Map<String, Object>> weekList = new ArrayList<Map<String, Object>>();
        try {
            for (int i = 0; i < 7; i++) {
                Map<String, Object> dateMap = new HashMap<String, Object>();
                if (i > 0) {
                    rightNow.add(Calendar.DAY_OF_MONTH, +1);
                }
                String rightDate = df.format(rightNow.getTime());
                String date = df2.format(rightNow.getTime());

                String week = dateToWeek.dayForWeek(rightDate);
                dateMap.put("date", date);
                dateMap.put("rightDate", rightDate);
                if (i == 0) {
                    dateMap.put("week", "今天");
                } else {
                    dateMap.put("week", week);
                }
                weekList.add(dateMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (coach != null ) {
            //将coach类和相关数据传入model
            view.addObject("coach", coach);
            view.addObject("fileData", fileDataList);
            view.addObject("courseList", courseList);
            view.addObject("flag", flag);
            view.addObject("isLogin", isLogin);
            //添加remarkList，可查询评价信息
            view.addObject("remarkList", remarkList);
            view.addObject("type_",type_);
            view.addObject("status_",status_);
        } else {
            view.setViewName("error/error");
        }
        return view;
    }

    /**
     * 获取教练课程列表,功能已移入coachDetail
     */
    @RequestMapping(value = "getCourseListByMemId")
    public void getCourseListByMemId() {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        String coachId = request.getParameter("coachId");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("coachId", coachId);
        List<Map<String, Object>> coachList = coachCourseService.getCourseListByMemId(map);
        if (coachList != null && coachList.size() > 0) {
            resultMap = getResultMap("1", "获取课程列表成功!", coachList);
        } else
            resultMap = getResultMap("0", "获取课程列表失败!");
        outJson(resultMap);

    }

    /*
    *跳转确认教练课程页面
    **/
    @RequestMapping("confirm_jl")
    public ModelAndView confirm_jl() {
        ModelAndView view = new ModelAndView();
        view.setViewName("coach/confirm_jl");
        //用户信息
        //从request中获取课程id和教练id
        //查出课程信息
        String courseId = request.getParameter("courseId");
        CoachCourse course = coachCourseService.get(Long.parseLong(courseId));
        Map<String, Object> coach=coachService.get(course.getCoachId());
        Object height_ = coach.get("height");
        double height=Double.parseDouble(height_.toString());
        int a = (int) height;
        coach.put("height", a);
        Object weight_ = coach.get("weight");
        double weight =Double.parseDouble(weight_.toString());
        int b = (int) weight;
        coach.put("weight", b);
        MemberUser memberUser = (MemberUser) sysCacheService.getMemberUserMap(request).get(ConstantUtil.SessionKeys.Member.value());
        view.addObject("course", course);
        view.addObject("coach", coach);
        view.addObject("memberId", memberUser.getId());
        return view;
    }

    /**
     * 约教练、根据运动类型获取教练列表
     */

    /*
    * 跳转约教练初始化页面
    * */
    @RequestMapping("coachListInit")
    public ModelAndView coachListInit() {
        ModelAndView view = new ModelAndView();
        view.setViewName("coach/coachListInit");
        List<Kinds> headKindsList = kindsService.getHeadKinds();
        String sportId=request.getParameter("id");
        view.addObject("sportId",sportId);
        view.addObject("headKindsList", headKindsList);
        return view;
    }

    @RequestMapping(value = "getCoachBySportId")
    public void getCoachListBySportId() {

        //Map<String, Object> resultMap = new HashMap<String, Object>();
        String sportId = request.getParameter("sportId");
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sportId", sportId);
        map.put("page", Integer.parseInt(page));
        map.put("pageSize", Integer.parseInt(pageSize));
        List<Map<String, Object>> coachList = coachService.getCoachListBySportId(map);
//        for (Map<String, Object> m : coachList) {
//            if ((Integer) m.get("sex") == 1) {
//                m.put("sex", "男");
//            } else {
//                m.put("sex", "女");
//            }
//
//        }

//        if (coachList != null && coachList.size() > 0) {
//
//            resultMap = getResultMap("1", "获取教练列表成功!", coachList);
//        } else
//            resultMap = getResultMap("0", "获取教练列表失败!");

        outJson(coachList);
    }

    //约教练跳转到搜索页面
    @RequestMapping(value = "coachSearchInit")
    public ModelAndView coachSearchInit(){
        ModelAndView view=new ModelAndView();
        view.setViewName("coach/coachSearchInit");
        return view;
    }

    /**
     * 教练搜索
     */
    @RequestMapping(value = "getCoachIndexResearch")
    public void coachIndexResearch() {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        String coachName = request.getParameter("coachName");//教练名称
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("coachName", coachName);
        map.put("page", Integer.parseInt(page));
        map.put("pageSize", Integer.parseInt(pageSize));
        List<Map<String, Object>> coachList = coachService.getCoachIndexResearch(map);
        if (coachList != null && coachList.size() > 0) {
            resultMap = getResultMap("1", "显示列表成功!", coachList);
        } else {
            resultMap = getResultMap("0", "显示列表失败!");
        }

        outJson(resultMap);
    }


    /**
     * 热门教练
     */
    @RequestMapping(value = "getRecommendCoach")
    public void getRecommendCoach() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        String zoneName = request.getParameter("zoneName");
        map.put("zoneName", zoneName);
        List<Map<String, Object>> list = coachService.getRecommendCoach(map);
        if (list.size() > 0) {
            resultMap = getResultMap("1", "获取热门教练成功！", list);
        } else {
            resultMap = getResultMap("0", "获取热门教练失败！");
        }
        outJson(resultMap);
    }


    /**
     * 根据年龄搜索教练
     */
    @RequestMapping(value = "getCoachByAge")
    public void getCoachByAge() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        String kindsId = request.getParameter("kindsId");
        String isAge = "1";
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        map.put("sportId", kindsId);
        map.put("isAge", isAge);
        map.put("page", Integer.parseInt(page));
        map.put("pagesize", Integer.parseInt(pagesize));
        List<Map<String, Object>> coachList = coachService.getListPage(map);
//        if (coachList != null && coachList.size() > 0) {
//            resultMap = getResultMap("1", "获取教练成功!", coachList);
//        } else {
//            resultMap = getResultMap("0", "获取教练失败!");
//        }
        outJson(coachList);
    }


    /**
     * 根据性别查询教练
     */
    @RequestMapping(value = "getCoachBySex")
    public void getCoachBySex() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        String kindsId = request.getParameter("kindsId");
        String sex = request.getParameter("sex");
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        map.put("sportId", kindsId);
        map.put("sex", sex);
        map.put("page", Integer.parseInt(page));
        map.put("pagesize", Integer.parseInt(pagesize));
        List<Map<String, Object>> coachList = coachService.getListPage(map);
//        if (coachList != null && coachList.size() > 0) {
//            resultMap = getResultMap("1", "获取教练成功!", coachList);
//        } else {
//            resultMap = getResultMap("0", "获取教练失败!");
//        }

        outJson(coachList);
    }

    /*
    * 生成教练课程订单
    *   memberId:10//会员id(买家id)
        contact:联系人(买家)
        telephone:18639716815(联系人电话)
        goodsId:10//购买的该教练课程的id
        orderAmount:100.00//教练课程价格(订单总金额)
    * */
    @RequestMapping(value = "createCoachOrder")
    public void createCoachOrder(@ModelAttribute Order order) {

        outJson(orderService.createCoachOrder(request, order));
    }
    /*跳转教练课程列表页面*/
    @RequestMapping("mycourselist")
    public ModelAndView mycourselist() {
        ModelAndView view = new ModelAndView();
        view.setViewName("coach/mycourselist");
        Map<String, Object> map = new HashMap<String, Object>();
        String memberId=request.getParameter("memberId");
        MemberUser memberUser1 = memberUserService.get(Long.parseLong(memberId));
        Long coachId = memberUser1.getData4();
        map.put("coachId",coachId);
        List<Map<String, Object>> courseList=coachCourseService.getCourseListByMemId(map);
       // Object coachCourseId=courseList.get(0);
        view.addObject("memberId", memberId);
        //view.addObject("coachCourseId", coachCourseId);
        view.addObject("coachId", coachId);
        return view;
    }
    /*跳转教练课程列表页面*/
    @RequestMapping("mycourse")
    public ModelAndView mycourse() {
        ModelAndView view = new ModelAndView();
         view.setViewName("coach/mycourse");
        String flag=request.getParameter("flag");
        String memberId=request.getParameter("memberId");
        String coachCourseId = request.getParameter("coachCourseId");
        /*
        if(flag=="add"){
            return view;
        }*/
        Map<String, Object> map = new HashMap<String, Object>();
        MemberUser memberUser1 = memberUserService.get(Long.parseLong(memberId));
        Long coachId = memberUser1.getData4();
        /*map.put("coachId",coachId);
        List<Map<String, Object>> courseList=coachCourseService.getCourseListByMemId(map);*/
        if(!flag.equals("add")){
            CoachCourse coachCourse = coachCourseService.get(Long.parseLong(coachCourseId));
            view.addObject("coachCourse", coachCourse);
        }
        view.addObject("coachId",coachId);
        view.addObject("memberId",memberId);
        return view;
    }
    /**
     * 添加/修改教练课程信息
     * @param coachCourse
     */
    @RequestMapping(value="saveCoachCourse")
    public void saveCoachCourse(@ModelAttribute CoachCourse coachCourse){
        outJson(coachCourseService.sava(request,coachCourse));
    }

    /**
     * 删除课程
     */
    @RequestMapping(value="deleteCoachCourse")
    public void deleteCoachCourse(){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        String id = request.getParameter("id");
        CoachCourse coachCourse = coachCourseService.get(Long.parseLong(id));
        coachCourse.setUpdateDate(new Date());
        coachCourse.setStatus(-1);
        int flag = coachCourseService.update(coachCourse);
        if(flag>0){
            resultMap = getResultMap("1","删除成功");
        }else{
            resultMap = getResultMap("0","删除失败，请重试");
        }
        outJson(resultMap);
    }

    @RequestMapping(value="getCoachCourse")
    public void getCoachCourse(){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        String id = request.getParameter("id");
        CoachCourse coachCourse = coachCourseService.get(Long.parseLong(id));
        if(null!=coachCourse){
            resultMap = getResultMap("1","获取课程信息成功",coachCourse);
        }else {
            resultMap = getResultMap("0","获取课程信息失败");
        }
        outJson(resultMap);
    }

}

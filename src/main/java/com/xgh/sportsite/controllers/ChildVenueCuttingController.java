package com.xgh.sportsite.controllers;

import com.xgh.sportsite.basic.BaseController;
import com.xgh.sportsite.entity.ChildVenue;
import com.xgh.sportsite.entity.MemberUser;
import com.xgh.sportsite.entity.Venue;
import com.xgh.sportsite.services.IChildVanueService;
import com.xgh.sportsite.services.IChildVenueCuttingService;
import com.xgh.sportsite.services.IVenueService;
import com.xgh.sportsite.util.ConstantUtil;
import com.xgh.sportsite.util.DateToWeek;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2016/12/9.
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/childVenueCutting")
public class ChildVenueCuttingController extends BaseController{

    private Logger logger = Logger.getLogger(MemberUserController.class);

    @Autowired
    protected IChildVenueCuttingService childVenueCuttingService;

    @Autowired
    protected IChildVanueService childVanueService;

    @Autowired
    protected IVenueService venueService;

    /*
    * 获取场馆的场次，ModelAndView
    * */
    @RequestMapping(value="getVenueCutting")
    public ModelAndView getVenueCutting(){
        ModelAndView view=new ModelAndView();
        view.setViewName("venue/venueCutting");
        String id = request.getParameter("id");
        String selectDate = request.getParameter("selectDate");
        String kindsName=request.getParameter("kindsName");
        String address=request.getParameter("address");
        //MemberUser memberUser=(MemberUser) sysCacheService.getMemberUserMap(request).get(ConstantUtil.SessionKeys.Member.value());
        //view.addObject("memberId",memberUser.getId());
        view.addObject("id",id);
        view.addObject("selectDate",selectDate);
        view.addObject("kindsName",kindsName);
        view.addObject("address",address);
        return view;
    }

    @RequestMapping(value="getVenueCuttings")
    public void getVenueCuttings(){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> outMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        String id = request.getParameter("id");
        String selectDate = request.getParameter("selectDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        ChildVenue childVenue = childVanueService.get(Long.parseLong(id));
        Venue venue = venueService.get(childVenue.getParentId());
        String startTime = venue.getStartTime();
        String endTime = venue.getEndTime();
        map.put("parentId",childVenue.getParentId());
        map.put("sportId",childVenue.getSportId());
        List<Map<String,Object>> venueList = childVanueService.getVenueByPid(map);
        try {
            for(Map<String,Object> venueMap:venueList){
                Map<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("childVenueId",Long.parseLong(venueMap.get("id")+""));
                Date selectDate1 = sdf.parse(selectDate);
                paramMap.put("selectDate",selectDate1);
                List<Map<String,Object>> cuttingList = childVenueCuttingService.getByVenueId(paramMap);
                String cutt="";
                for(int i=0;i<cuttingList.size();i++){
                    cutt+=cuttingList.get(i).get("timePeriod")+",";
                }
                if(cutt!=""){
                    cutt=cutt.substring(0,cutt.length()-1);
                }else{
                    cutt=",";
                }

                venueMap.put("cuttingList",cutt);
                venueMap.put("startTime",startTime);
                venueMap.put("endTime",endTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

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
        outMap.put("venueList",venueList);
        outMap.put("weekList",weekList);
        if(venueList.size()>0){
            resultMap = getResultMap("1","获取场次列表成功！",outMap);
        }else{
            resultMap = getResultMap("0","获取场次列表失败！");
        }
        outJson(resultMap);
    }

    @RequestMapping(value="getVenueAmount")
    public void getVenueAmount(){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        String cuttings = request.getParameter("cuttings").replaceAll(" ","");
        //将每个场馆进行分割
        String[] cutting = cuttings.split(";|；");
        double amount = 0;
        for(int i = 0;i<cutting.length;i++){
            //场馆和场次进行分割
            String[] idAndCutting = cutting[i].split(":|：");
            if(idAndCutting.length==2){
                ChildVenue childVenue = childVanueService.get(Long.parseLong(idAndCutting[0]));
                int cuttingCount = idAndCutting[1].split(",|，").length;
                amount += childVenue.getPrice()*cuttingCount;
            }
        }
        if(amount>0){
            resultMap = getResultMap("1","获取总价成功！",amount);
        }else{
            resultMap = getResultMap("0","获取总价失败！");
        }
        outJson(resultMap);

    }



}

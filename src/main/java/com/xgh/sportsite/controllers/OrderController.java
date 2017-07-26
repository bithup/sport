package com.xgh.sportsite.controllers;

import com.xgh.sportsite.basic.BaseController;
import com.xgh.sportsite.entity.*;
import com.xgh.sportsite.services.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2016/12/12.
 */
@Controller
@Scope("prototype")
@RequestMapping("/order/")
public class OrderController extends BaseController {

    private Logger logger = Logger.getLogger(MemberUserController.class);

    @Autowired
    protected ICoachCourseService coachCourseService;

    @Autowired
    protected IOrderService orderService;

    @Autowired
    protected IMemberUserService memberUserService;

    @Autowired
    protected ICoachService coachService;

    @Autowired
    protected IVenueService venueService;

    @Autowired
    protected IRefundService refundService;


    @Autowired
    protected IRemarkService remarkService;

    @Autowired
    protected IChildVanueService childVanueService;

    @Autowired
    protected IActivityService activityService;

    @Autowired
    protected IKindsService kindsService;

    @Autowired
    protected IChildVenueCuttingService childVenueCuttingService;


    @RequestMapping(value = "createVenueOrder")
    public void createVenueOrder(@ModelAttribute Order order) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = orderService.createVenueOrder(request, order);
        if (Long.parseLong(map.get("orderId") + "") > 0) {
            resultMap = getResultMap("1", "订单提交成功!", Long.parseLong(map.get("orderId") + ""));
        } else {
            resultMap = getResultMap("0", "订单提交成功!");
        }
        outJson(resultMap);
    }


    /**
     * 订单列表卖家版
     */
    @RequestMapping(value = "getListAsShop")
    public void getListAsShop() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        String memberId = request.getParameter("memberId");
        String orderStatus = request.getParameter("orderStatus");
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        map.put("orderStatus", orderStatus);
        map.put("page", Integer.parseInt(page));
        map.put("pagesize", Integer.parseInt(pagesize));

        MemberUser memberUser = memberUserService.get(Integer.parseInt(memberId));
        if (memberUser != null) {
            int type = memberUser.getData7();
            String telPhone = memberUser.getAccount();
            if (type == 3) {
                List<Venue> venueList = venueService.getVenueByPhone(telPhone);
                if (venueList != null && venueList.size() == 1) {
                    Venue venue = venueList.get(0);
                    map.put("shopId", venue.getId());
                    List<Order> list = orderService.getListPage(map);
                    if (list != null && list.size() > 0) {
                        List<Map<String, Object>> orderGoodsList = orderGoodsList(list);
                        resultMap = getResultMap("1", "获取订单列表成功", orderGoodsList);
                    } else {
                        resultMap = getResultMap("0", "暂无数据！");
                    }
                } else {
                    resultMap = getResultMap("0", "账号错误！");
                }

            } else if (type == 2) {
                List<Coach> coachList = coachService.getCoachByPhone(telPhone);
                if (coachList != null && coachList.size() == 1) {
                    Coach coach = coachList.get(0);
                    map.put("shopId", coach.getId());
                    List<Order> list = orderService.getListPage(map);
                    if (list != null && list.size() > 0) {
                        List<Map<String, Object>> orderGoodsList = orderGoodsList(list);
                        resultMap = getResultMap("1", "获取订单列表成功", orderGoodsList);
                    } else {
                        resultMap = getResultMap("0", "暂无数据！");
                    }
                } else {
                    resultMap = getResultMap("0", "账号错误！");
                }
            }
        } else {
            resultMap = getResultMap("0", "无此账号！");
        }

        outJson(resultMap);

    }


    /**
     * 订单列表买家版
     */
    @RequestMapping(value = "getListAsMember")
    public void getListAsMember() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        String memberId = request.getParameter("memberId");
        String orderStatus = request.getParameter("orderStatus");
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        map.put("memberId", memberId);
        map.put("orderStatus", orderStatus);
        map.put("page", Integer.parseInt(page));
        map.put("pagesize", Integer.parseInt(pagesize));
        List<Order> list = orderService.getListPage(map);
        if (list != null && list.size() > 0) {
            List<Map<String, Object>> orderGoodsList = orderGoodsList(list);
            resultMap = getResultMap("1", "获取订单列表成功", orderGoodsList);
        } else {
            resultMap = getResultMap("0", "暂无数据！");
        }
        outJson(resultMap);

    }


    public List<Map<String, Object>> orderGoodsList(List<Order> list) {
        List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
        for (Order orderMap : list) {
            Map<String, Object> orderGoodsMap = new HashMap<String, Object>();
            orderGoodsMap.put("id", orderMap.getId());
            orderGoodsMap.put("orderStatus", orderMap.getOrderStatus());
            orderGoodsMap.put("orderNo", orderMap.getOrderNo());
            orderGoodsMap.put("contact", orderMap.getContact());
            orderGoodsMap.put("telephone", orderMap.getTelephone());
            orderGoodsMap.put("orderType", orderMap.getOrderType());
            orderGoodsMap.put("orderAmount", orderMap.getOrderAmount());
            orderGoodsMap.put("tradeNo", orderMap.getTradeNo());
            orderGoodsMap.put("payTime", orderMap.getPayTime());
            orderGoodsMap.put("isRefund", orderMap.getIsRefund());
            orderGoodsMap.put("goodsName", orderMap.getGoodsName());
            orderGoodsMap.put("goodsId", orderMap.getGoodsId());
            orderGoodsMap.put("createDate", orderMap.getCreateDate());
            orderGoodsMap.put("code", orderMap.getData3());
            if (orderMap.getOrderType() == 0) {
                String[] goodsIds = orderMap.getGoodsId().split(",|，");
                List<Map<String, Object>> vanueList = new ArrayList<Map<String, Object>>();
                for (int i = 0; i < goodsIds.length; i++) {
                    Map<String, Object> venueMap = new HashMap<String, Object>();
                    ChildVenue venue = childVanueService.get(Long.parseLong(goodsIds[i]));
                    venueMap.put("venueId", goodsIds[i]);
                    venueMap.put("venueNo", venue.getVenueNo() + "号场");
                    vanueList.add(venueMap);
                }
                ChildVenue childVenue = childVanueService.get(Long.parseLong(goodsIds[0]));
                Kinds kinds = kindsService.get(childVenue.getSportId());
                orderGoodsMap.put("kindName", kinds.getName());
                orderGoodsMap.put("headPath", childVenue.getPictureUrl());
                orderGoodsMap.put("headRealPath", childVenue.getPicRealPath());
                orderGoodsMap.put("noList", vanueList);
                orderGoodsMap.put("isFree", "0");
                orderGoodsMap.put("status", childVenue.getStatus());

            } else if (orderMap.getOrderType() == 1) {
                String coachCourseId = orderMap.getGoodsId();
                CoachCourse coachCourse = coachCourseService.get(Long.parseLong(coachCourseId));
                Map<String, Object> coach = coachService.get(coachCourse.getCoachId());
                orderGoodsMap.put("kindName", coach.get("kindsName"));
                orderGoodsMap.put("headPath", coach.get("pictureUrl"));
                orderGoodsMap.put("headRealPath", coach.get("picRealPath"));
                orderGoodsMap.put("noList", "");
                orderGoodsMap.put("isFree", "0");
                orderGoodsMap.put("status", coach.get("status"));
            } else if (orderMap.getOrderType() == 2) {
                String activityId = orderMap.getGoodsId();
                Activity activity = activityService.get(Long.parseLong(activityId));
                Kinds kinds = kindsService.get(activity.getSportId());
                orderGoodsMap.put("kindName", kinds.getName());
                orderGoodsMap.put("headPath", activity.getActivityPath());
                orderGoodsMap.put("headRealPath", activity.getActivityRealPath());
                orderGoodsMap.put("noList", "");
                orderGoodsMap.put("isFree", activity.getIsFree());
                orderGoodsMap.put("status", activity.getStatus());
            }
            newList.add(orderGoodsMap);
        }

        return newList;
    }


    /**
     * 订单详情
     */
    @RequestMapping(value = "getOrderDetail")
    public void getOrderDetail() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String orderId = request.getParameter("orderId");
        Order order = orderService.get(Long.parseLong(orderId));
        if (order != null) {
            resultMap = getResultMap("1", "获取订单详情成功！", order);
        } else {
            resultMap = getResultMap("0", "获取订单详情失败！");
        }
        outJson(resultMap);

    }


    /**
     * 删除订单、撤销订单
     */
    @RequestMapping(value = "deleteOrder")
    public void deleteOrder() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String orderId = request.getParameter("orderId");
        Order order = orderService.get(Long.parseLong(orderId));
        order.setStatus(-1);
        int flag = orderService.update(order);
        if (flag > 0) {
            resultMap = getResultMap("1", "删除成功！");
        } else {
            resultMap = getResultMap("0", "删除失败，请重试！");
        }
        outJson(resultMap);

    }

    /**
     * 确认订单(由卖家确认)
     */
    @Transactional
    @RequestMapping(value = "confirmOrder")
    public void confirmOrder() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String orderId = request.getParameter("orderId");
        String code = request.getParameter("code");
        Date date = new Date();
        Order order = orderService.get(Long.parseLong(orderId));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (null != code && !"".equals(code) && code.equals(order.getData3())) {//验证确认码是否正确

            //修改订单状态
            order.setOrderStatus(2);
            order.setData2(df.format(date));
            order.setUpdateDate(date);
            int flag = orderService.update(order);

            //将钱加入商家钱包
            if (order.getOrderType() == 0) {//场馆
                Venue venue = venueService.get(order.getData4());
                if (null != venue) {
                    List<MemberUser> list = memberUserService.getRepeatAccount(venue.getMobile());
                    MemberUser memberUser = list.get(0);
                    if (order.getOrderAmount() > 1) {
                        memberUser.setPurseBalance(memberUser.getPurseBalance() + order.getOrderAmount() - 1);//每个订单给平台一块钱
                    } else {
                        memberUser.setPurseBalance(memberUser.getPurseBalance() + order.getOrderAmount());
                    }
                    memberUser.setUpdateDate(date);
                    memberUserService.update(memberUser);

                    //场馆销量加1
                    String goodsIds = order.getGoodsId();
                    String[] ids = goodsIds.split(",|，");
                    for (int i = 0; i < ids.length; i++) {
                        String id = ids[i];
                        if (null != id && !"".equals(id)) {
                            ChildVenue childVenue = childVanueService.get(Long.parseLong(id));
                            childVenue.setData5(childVenue.getData5() + 1);
                            childVenue.setUpdateDate(new Date());
                            childVanueService.update(childVenue);
                        }
                    }
                }
            } else if (order.getOrderType() == 1) {//教练
                Map<String, Object> coach = coachService.get(order.getData4());
                String telPhone = coach.get("telPhone").toString();
                List<MemberUser> list = memberUserService.getRepeatAccount(telPhone);
                MemberUser memberUser = list.get(0);
                if (order.getOrderAmount() > 1) {
                    memberUser.setPurseBalance(memberUser.getPurseBalance() + order.getOrderAmount() - 1);//每个订单给平台一块钱
                } else {
                    memberUser.setPurseBalance(memberUser.getPurseBalance() + order.getOrderAmount());
                }
                memberUser.setUpdateDate(date);
                memberUserService.update(memberUser);

                //销量加1
                Coach coach_ = coachService.getCoachByCoachId(Long.parseLong(coach.get("id").toString()));
                coach_.setData7(coach_.getData7() + 1);
                coach_.setUpdateDate(new Date());
                coachService.update(coach_);
            }

            if (flag > 0) {
                resultMap = getResultMap("1", "操作成功");
            } else {
                resultMap = getResultMap("0", "操作失败，请重试");
            }
        } else {
            resultMap = getResultMap("0", "消费凭证码输入不正确");
        }
        outJson(resultMap);
    }

    /*
    * 确认订单页面跳转
    * */
    @RequestMapping(value = "confirm_dd")
    public ModelAndView confirm_dd() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/confirm_dd");
        String orderId = request.getParameter("orderId");
        view.addObject("orderId", orderId);
        String memberId = sysCacheService.getCurrentMemberUserId(request) + "";
        view.addObject("memberId", memberId);
        return view;
    }

    /**
     * 申请退款
     */
    @Transactional
    @RequestMapping(value = "refundMoney")
    public void refundMoney() {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        String orderId = request.getParameter("orderId");
        String reason = request.getParameter("reason");
        String type = request.getParameter("type");//退款方式：1按金额；2按百分比
        String refundAmount = request.getParameter("refundAmount");//申请退款的金额
        String percent = request.getParameter("percent");//申请退款的百分比

        //查出此订单是否已经申请过退款
        map.put("orderId", orderId);
        List<Refund> list = refundService.getByOrderId(map);

        if (list != null && list.size() > 0) {
            resultMap = getResultMap("2", "已经申请过退款！");
        } else {
            //申请退款的订单
            Order order = orderService.get(Long.parseLong(orderId));

            Refund refund = new Refund();
            double amount = order.getOrderAmount();//订单总金额
            if (null != type && !"".equals(type)) {
                refund.setData3(Integer.parseInt(type));
                if (type.equals("1")) {//按金额退
                    double fundAmount_ = Double.parseDouble(refundAmount);
                    if (fundAmount_ > (amount - 1)) {
                        resultMap = getResultMap("-1", "退款金额不能大于可退金额");
                        outJson(resultMap);
                        return;
                    } else {
                        refund.setRefunndMoney(fundAmount_);
                    }
                } else if (type.equals("2")) {//按百分比退
                    double percent_ = Double.parseDouble(percent);
                    double fundAmount_ = amount * (percent_ / 100);
                    if (fundAmount_ > (amount - 1)) {
                        resultMap = getResultMap("-1", "退款金额不能大于可退金额");
                        outJson(resultMap);
                        return;
                    } else {
                        refund.setRefunndMoney(fundAmount_);
                    }
                }
            } else {
                refund.setRefunndMoney(order.getOrderAmount() - 1);
            }

            //退款
            refund.setReason(reason);
            refund.setMemberId(order.getMemberId());
            refund.setOrderId(order.getId());
            refund.setOrderNo(order.getOrderNo());
            refund.setStatus(0);
            Date date = new Date();
            refund.setCreateTime(date);
            refund.setUpdateTime(date);
            int flag = refundService.insert(refund);

            //修改订单
            order.setIsRefund(2);
            order.setOrderStatus(4);
            order.setUpdateDate(new Date());
            orderService.update(order);

            if (flag > 0) {
                resultMap = getResultMap("1", "申请退款成功！");
            } else {
                resultMap = getResultMap("0", "申请退款失败！");
            }
        }
        outJson(resultMap);
    }

    //跳转评价页面
    @RequestMapping(value = "evaluate")
    public ModelAndView evaluate() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/evaluate");
        String orderId = request.getParameter("orderId");
        String goodsId = request.getParameter("goodsId");
        view.addObject("goodsId", goodsId);
        view.addObject("orderId", orderId);
        String memberId = sysCacheService.getCurrentMemberUserId(request) + "";
        view.addObject("memberId", memberId);
        return view;
    }

    @RequestMapping(value = "remark")
    public void remark() {
        Map<String,Object> resultMap = new HashMap<String, Object>();
        String orderId = request.getParameter("orderId");
        String dataId = request.getParameter("dataId");
        String kind = request.getParameter("kind");
        Order order = orderService.get(Long.parseLong(orderId));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int flag = 0;

        Remark remark = new Remark();
        remark.setOrderId(order.getId());

        remark.setKind(Integer.parseInt(kind));
        remark.setType(order.getOrderType());
        Date date = new Date();
        remark.setCreateDate(date);
        remark.setUpdateDate(date);
        if(order.getOrderType()==0){//场馆
            String goodsId = order.getGoodsId();
            String[] ids = goodsId.split(",|，");
            for(int i=0;i<ids.length;i++){
                remark.setDataId(Long.parseLong(ids[i]));
                flag = remarkService.insert(remark);
            }
        }else{
            remark.setDataId(Long.parseLong(dataId));
            flag = remarkService.insert(remark);
        }
        order.setOrderStatus(3);
        Date date1 = new Date();
        order.setData1(df.format(date1));
        order.setUpdateDate(date1);
        orderService.update(order);
        if(flag>0){
            resultMap = getResultMap("1","评价成功！");
        }else{
            resultMap = getResultMap("0","评价失败，请重试！");
        }
        outJson(resultMap);
    }

    //订单详情
    @RequestMapping(value = "orderDetail")
    public ModelAndView orderDetail() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/orderDetail");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        String orderId = request.getParameter("orderId");
        Order order = orderService.get(Long.parseLong(orderId));
        map.put("id", order.getId());
        map.put("memberId", order.getMemberId());
        map.put("orderNo", order.getOrderNo());
        map.put("contact", order.getContact());
        map.put("telephone", order.getTelephone());
        map.put("orderType", order.getOrderType());
        map.put("goodsId", order.getGoodsId());
        map.put("goodsName", order.getGoodsName());
        map.put("orderAmount", order.getOrderAmount());
        map.put("beginDate", order.getBeginDate());
        map.put("endDate", order.getEndDate());
        map.put("payAccount", order.getPayAccount());
        map.put("tradeNo", order.getTradeNo());
        map.put("payTime", order.getPayTime());
        map.put("isRefund", order.getIsRefund());
        map.put("orderStatus", order.getOrderStatus());
        map.put("createDate", order.getCreateDate());
        map.put("updateDate", order.getUpdateDate());
        map.put("closingTime", order.getData1());
        map.put("confirmTime", order.getData2());
        if (order.getOrderStatus() == 1) {
            map.put("code", order.getData3());
        }
        if (order.getOrderType() == 0) {//场馆
            String goodsId = order.getGoodsId();
            String[] ids = goodsId.split(",");
            Long id = Long.parseLong(ids[0]);
            ChildVenue childVenue = childVanueService.get(id);
            Zone zone = zoneService.get(venueService.get(childVenue.getParentId()).getZoneId());
            map.put("picRealPath", childVenue.getPicRealPath());
            map.put("zoneName", zone.getName());
            map.put("salesPrice", childVenue.getSalesPrice());
            List<Map<String, Object>> cuttingList = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < ids.length; i++) {
                Map<String, Object> params = new HashMap<String, Object>();
                Map<String, Object> childVenueMap = new HashMap<String, Object>();
                ChildVenue venue = childVanueService.get(Long.parseLong(ids[i]));
                params.put("childVenueId", venue.getId());
                params.put("selectDate", order.getBeginDate());
                params.put("orderId", orderId);
                List<Map<String, Object>> list = childVenueCuttingService.getByOrderId(params);
                StringBuffer sb = new StringBuffer();
                for (Map<String, Object> cuttingMap : list) {
                    sb.append(cuttingMap.get("timePeriod") + ",");
                }
                String cutts = sb.toString();
                String[] cuttings = cutts.substring(0, cutts.length() - 1).split(",");
                String a = cuttings[0];
                String timePeriods = a + ":00";
                //int x = 0;
                if (cuttings.length > 1) {
                    int c = Integer.parseInt(a);
                    for (int j = 1; j < cuttings.length; j++) {
                        int b = Integer.parseInt(cuttings[j]);
                        if (c + 1 == b && j != cuttings.length - 1) {
                            c = Integer.parseInt(cuttings[j]);
                            continue;
                        } else if (c + 1 != b && j != cuttings.length - 1) {
                            timePeriods += "-" + (c + 1) + ":00," + cuttings[j] + ":00-";
                            c = Integer.parseInt(cuttings[j]);
                           /* if(a.equals(cuttings[j-1])){
                                timePeriods += "-"+(c+1)+":00,"+cuttings[j]+":00-"+(Integer.parseInt(cuttings[j])+1)+":00";
                            }else if(j==cuttings.length-1){
                                timePeriods += "-"+ (Integer.parseInt(cuttings[j])+1)+":00";
                            } else{
                                timePeriods += "-"+ (Integer.parseInt(cuttings[j-1])+1)+":00,"+cuttings[j]+":00";
                            }
                            a = cuttings[j];*/
                            //x = j;
                        } else if (c + 1 == b && j == cuttings.length - 1) {
                            timePeriods += "-" + (Integer.parseInt(cuttings[j]) + 1) + ":00";
                        } else {
                            timePeriods += "-" + (c + 1) + ":00," + cuttings[j] + ":00-" + (Integer.parseInt(cuttings[j]) + 1) + ":00";
                        }
                    }
                } else {
                    timePeriods += "-" + (Integer.parseInt(a) + 1) + ":00";
                }
                childVenueMap.put("venueNo", venue.getVenueNo() + "号场");
                SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
                childVenueMap.put("selectDate", df1.format(order.getBeginDate()));
                childVenueMap.put("timePeriods", timePeriods);
                childVenueMap.put("venueAmount", list.size() * venue.getSalesPrice());
                cuttingList.add(childVenueMap);
            }
            map.put("cuttingList", cuttingList);

        } else if (order.getOrderType() == 1) {//教练
            long id = Long.parseLong(order.getGoodsId());
            CoachCourse coachCourse = coachCourseService.get(id);
            Map<String, Object> coach = coachService.get(coachCourse.getCoachId());
            map.put("coachId", coach.get("id"));
            map.put("coachName", coach.get("name"));
            map.put("picRealPath", coach.get("picRealPath"));
            map.put("sex", coach.get("sex"));
            map.put("intro", coach.get("intro"));
            map.put("sportName", coach.get("kindsName"));
            map.put("courseType", coachCourse.getCourseType());

        } else if (order.getOrderType() == 2) {//活动
            long id = Long.parseLong(order.getGoodsId());
            Activity activity = activityService.get(id);
            map.put("picRealPath", activity.getActivityRealPath());
            map.put("activityAddress", activity.getActivityAddress());
            map.put("isFree", activity.getIsFree());
            map.put("startDate", activity.getStartDate());
            map.put("endDate", activity.getEndDate());
        }
        view.addObject("order", order);
        view.addObject("map", map);
        return view;

    }

}

package com.xgh.sportsite.services;

import com.xgh.sportsite.basic.BaseService;
import com.xgh.sportsite.dao.*;
import com.xgh.sportsite.entity.*;
import com.xgh.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.httpclient.HttpVersion.parse;

/**
 * Created by Administrator on 2016/12/12.
 */
@Service("orderService")
public class OrderServiceImpl extends BaseService implements IOrderService {

    @Autowired
    protected IOrderDao orderDao;

    @Autowired
    protected IActivityDao activityDao;

    @Autowired
    protected ICoachCourseDao coachCourseDao;

    @Autowired
    protected IChildVenueDao childVenueDao;

    @Autowired
    protected IChildVenueCuttingDao childVenueCuttingDao;

    public Order get(long id) {
        return orderDao.get(id);
    }

    public int delete(long id) {
        return orderDao.delete(id);
    }

    public int insert(Order order) {
        return orderDao.insert(order);
    }

    public int update(Order order) {
        return orderDao.update(order);
    }
    public int getAcountByGoodsId(Map<String, Object> map) {
        return orderDao.getAcountByGoodsId(map);
    }


    public Map<String, Object> createVenueOrder(HttpServletRequest request, Order order) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        String memberId = request.getParameter("memberId");
        String cuttings = request.getParameter("cuttings");
        String selectDate = request.getParameter("selectDate");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();
        order.setMemberId(Long.parseLong(memberId));
        order.setOrderNo(OrderUtil.getOrderNo());
        order.setOrderType(0);
        order.setOrderStatus(0);
        order.setCreateDate(date);
        order.setUpdateDate(date);
        order.setStatus(1);
        try {
            order.setBeginDate(sdf.parse(selectDate));
            order.setEndDate(sdf.parse(selectDate));
            orderDao.insert(order);
            long id = order.getId();
            //将每个场馆进行分割
            String[] cutting = cuttings.split(";|；");
            double amount = 0;
            String goodsId = "";
            for (int i = 0; i < cutting.length; i++) {
                //场馆和场次进行分割
                String[] idAndCutting = cutting[i].split(":|：");
                if (idAndCutting.length == 2) {
                    if (goodsId == "") {
                        goodsId += idAndCutting[0];
                    } else {
                        goodsId += "," + idAndCutting[0];
                    }
                    ChildVenue childVenue = childVenueDao.get(Long.parseLong(idAndCutting[0]));
                    order.setGoodsName(childVenue.getVenueName());
                    order.setData4(childVenue.getParentId());
                    String[] timePeriods = idAndCutting[1].split(",|，");
                    int cuttingCount = timePeriods.length;
                    amount += childVenue.getSalesPrice() * cuttingCount;

                    //往场次表插入占用记录
                    ChildVenueCutting childVenueCutting = new ChildVenueCutting();
                    childVenueCutting.setChildVenueId(childVenue.getId());
                    childVenueCutting.setOrderId(id);
                    childVenueCutting.setStatus(1);
                    childVenueCutting.setStartDate(sdf.parse(selectDate));
                    childVenueCutting.setCreateDate(date);
                    childVenueCutting.setUpdateDate(date);
                    for (int j = 0; j < cuttingCount; j++) {
                        childVenueCutting.setTimePeriod(timePeriods[j]);
                        childVenueCuttingDao.insert(childVenueCutting);
                    }
                }
            }

            order.setGoodsId(goodsId);
            order.setOrderAmount(amount);
            orderDao.update(order);

            resultMap.put("orderId", id);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * 生成活动订单
     *
     * @param request
     * @param order
     * @return
     */
    public Map<String, Object> createActiveOrder(HttpServletRequest request, Order order) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        Activity activity = activityDao.get(Long.parseLong(order.getGoodsId()));
        order.setGoodsName(activity.getActivityName());//活动名称
        order.setOrderNo(OrderUtil.getOrderNo());//订单号
        order.setBeginDate(activity.getStartDate());//开始时期
        order.setEndDate(activity.getEndDate());//截止日期
        order.setCreateDate(new Date());
        order.setUpdateDate(new Date());
        order.setOrderType(2);//订单类型
        if (activity.getIsFree() == 0) {//付款的设置订单状态为代付款
            order.setOrderStatus(0);
        } else if (activity.getIsFree() == 2) {//AA制的设置订单状态为待评价
            order.setOrderStatus(2);
        } else if (activity.getIsFree() == 1) {
            if (activity.getActivityType() == 0) {
                order.setOrderStatus(3);//平台免费的设置订单状态为待评价
            } else {
                order.setOrderStatus(2);//个人免费的设置订单状态为已完成
            }
        }
        order.setIsRefund(0);
        order.setStatus(1);
        if (activity.getUserId() != 0) {
            order.setData4(activity.getUserId());//卖家id
        } else {
            order.setData4(0L);
        }
        //判断收费方式，免费以及AA不跳转支付页面
        if (activity.getIsFree() == 0) {//收费活动

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("memberId", order.getMemberId());
            map.put("goodsId", order.getGoodsId());
            long goodsCount = orderDao.getActivityCount(map);
//            if (goodsCount > 0) {
//                resultMap = getResultMap("-1", "您已报名!");
//                return resultMap;
//            }
            int flag = orderDao.insert(order);
            if (flag > 0) {
                resultMap = getResultMap("1", "请选择支付方式",order.getId());
            } else {
                resultMap = getResultMap("0", "活动订单生成失败!");
            }
        }
        if (activity.getIsFree() == 1) {//免费活动

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("memberId", order.getMemberId());
            map.put("goodsId", order.getGoodsId());
            long goodsCount = orderDao.getActivityCount(map);
            if (goodsCount > 0) {
                resultMap = getResultMap("-1", "您已报名!");
                return resultMap;
            }
            int flag = orderDao.insert(order);
            if (flag > 0) {
                resultMap = getResultMap("-1", "该活动为免费活动，无需支付！订单提交成功！",order.getId());
            } else {
                resultMap = getResultMap("0", "活动订单生成失败!");
            }
        }
        if (activity.getIsFree() == 2) {//AA制活动

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("memberId", order.getMemberId());
            map.put("goodsId", order.getGoodsId());
            long goodsCount = orderDao.getActivityCount(map);
//            if (goodsCount > 0) {
//                resultMap = getResultMap("-1", "您已报名!");
//                return resultMap;
//            }
            int flag = orderDao.insert(order);
            if (flag > 0) {
                resultMap = getResultMap("-1", "该活动为AA制活动，无需支付！订单提交成功！",order.getId());
            } else {
                resultMap = getResultMap("0", "活动订单生成失败!");
            }
        }

        return resultMap;
    }


    public Map<String, Object> getIdByOrderNo(Map<String, Object> map) {
        return orderDao.getIdByOrderNo(map);
    }

    public List<Order> getListPage(Map<String, Object> map) {
        return orderDao.getListPage(map);
    }

    public Map<String, Object> createCoachOrder(HttpServletRequest request, Order order) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        CoachCourse coachCourse = coachCourseDao.get(Long.parseLong(order.getGoodsId()));
        if (coachCourse != null) {
            order.setGoodsName(coachCourse.getCourseName());//课程名称
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date startDate = simpleDateFormat.parse(coachCourse.getStartDate());
                Date endDate = simpleDateFormat.parse(coachCourse.getEndDate());
                order.setBeginDate(startDate);
                order.setEndDate(endDate);
                order.setData4(coachCourse.getCoachId());//卖家id
            } catch (Exception e) {

            }
        }
        String orderNo=OrderUtil.getOrderNo();
        order.setOrderNo(orderNo);
        order.setCreateDate(new Date());
        order.setUpdateDate(new Date());
        order.setOrderType(1);//订单类型
        order.setOrderStatus(0);
        order.setIsRefund(0);
        order.setStatus(1);

        int flag = orderDao.insert(order);

        if (flag > 0) {
            resultMap = getResultMap("1", "教练课程生成订单成功!",order.getId());
        } else {
            resultMap = getResultMap("0", "教练课程生成订单失败!");
        }
        return resultMap;

    }

    @Transactional
    public Map<String, Object> createTestTransactionOrder(HttpServletRequest request, Order order) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int flag = orderDao.insert(order);
        Order order1 = orderDao.get(100000);
        order1.setCreateDate(new Date());
        order1.setContact("李四........");
        orderDao.update(order1);

        if (flag > 0) {
            resultMap = getResultMap("1", "测试生成订单成功!");
        } else {
            resultMap = getResultMap("0", "测试生成订单失败!");
        }
        return resultMap;
    }
}

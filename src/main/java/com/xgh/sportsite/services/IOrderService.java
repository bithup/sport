package com.xgh.sportsite.services;

import com.xgh.sportsite.entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/12.
 */
public interface IOrderService {


    public Map<String, Object> getIdByOrderNo(Map<String, Object> map);

    public Order get(long id);

    public int delete(long id);

    public int insert(Order order);

    public int update(Order order);

    public Map<String, Object> createVenueOrder(HttpServletRequest request, Order order);

    /**
     * @param request
     * @param order
     * @return
     */
    public Map<String, Object> createActiveOrder(HttpServletRequest request, Order order);


    /**
     * @param request
     * @param order   教练订单生成
     * @return
     */
    public Map<String, Object> createCoachOrder(HttpServletRequest request, Order order);

    /**
     * 测试
     */
    public Map<String, Object> createTestTransactionOrder(HttpServletRequest request, Order order);



    public List<Order> getListPage(Map<String, Object> map);
    int getAcountByGoodsId(Map<String,Object> map);
}

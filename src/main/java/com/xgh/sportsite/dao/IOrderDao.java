package com.xgh.sportsite.dao;

import com.xgh.sportsite.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/12.
 */
public interface IOrderDao {


    public Map<String, Object> getIdByOrderNo(Map<String, Object> map);

    public List<Map<String, Object>> getActivitySignDetail(Map<String, Object> map);

    public long getActivityCount(Map<String, Object> map);

    public Order get(long id);

    public int delete(long id);

    public int insert(Order order);

    public int update(Order order);

    public List<Order> getListPage(Map<String, Object> map);

    int getAcountByGoodsId(Map<String,Object> map);
}

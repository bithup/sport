package com.xgh.sportsite.dao.read;

import com.xgh.sportsite.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/12.
 */
public interface IOrderDaoR {

    public Order get(long id);


    public Map<String, Object> getIdByOrderNo(Map<String, Object> map);


    public long getActivityCount(Map<String, Object> map);


    public List<Map<String, Object>> getActivitySignDetail(Map<String, Object> map);

    public List<Order> getListPage(Map<String, Object> map);
    int getAcountByGoodsId(Map<String,Object> map);
}

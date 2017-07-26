package com.xgh.sportsite.dao;


import com.xgh.sportsite.dao.read.IOrderDaoR;
import com.xgh.sportsite.dao.write.IOrderDaoW;
import com.xgh.sportsite.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/12.
 */

@Service("orderDao")
public class OrderDaoImpl implements IOrderDao {

    @Autowired
    protected IOrderDaoR orderDaoR;

    @Autowired
    protected IOrderDaoW orderDaoW;

    /**
     * 查询该活动报名详情
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> getActivitySignDetail(Map<String, Object> map) {
        return orderDaoR.getActivitySignDetail(map);
    }

    public Map<String, Object> getIdByOrderNo(Map<String, Object> map) {
        return orderDaoR.getIdByOrderNo(map);
    }

    public Order get(long id) {
        return orderDaoR.get(id);
    }

    public int delete(long id) {
        return orderDaoW.delete(id);
    }

    public int insert(Order order) {
        return orderDaoW.insert(order);
    }

    public int update(Order order) {
        return orderDaoW.update(order);
    }

    public long getActivityCount(Map<String, Object> map) {
        return orderDaoR.getActivityCount(map);
    }


    public List<Order> getListPage(Map<String, Object> map) {
        return orderDaoR.getListPage(map);
    }
    public int getAcountByGoodsId(Map<String, Object> map) {
        return orderDaoR.getAcountByGoodsId(map);
    }
}

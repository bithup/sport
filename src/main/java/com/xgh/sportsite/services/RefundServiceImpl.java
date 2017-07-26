package com.xgh.sportsite.services;

import com.xgh.sportsite.dao.IOrderDao;
import com.xgh.sportsite.dao.IRefundDao;
import com.xgh.sportsite.entity.Order;
import com.xgh.sportsite.entity.Refund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/19.
 */
@Service("refundService")
public class RefundServiceImpl implements IRefundService {

    @Autowired
    protected IRefundDao refundDao;

    @Autowired
    protected IOrderDao orderDao;

    public Refund get(long id) {
        return refundDao.get(id);
    }

    public int insert(Refund refund) {
        return refundDao.insert(refund);
    }

    public int delete(long id) {
        return refundDao.delete(id);
    }

    public int update(Refund refund) {
        Refund refund_ = refundDao.getByBatchNo(refund.getBatchNo());
        Date date = new Date();
        refund_.setStatus(1);
        refund_.setRefundTime(date);
        refund_.setUpdateTime(date);
        refund_.setData1(refund.getData1());

        Order order = orderDao.get(refund_.getOrderId());
        order.setIsRefund(1);
        order.setOrderStatus(4);
        order.setUpdateDate(date);

        return refundDao.update(refund_);
    }

    public List<Refund> getByOrderId(Map<String, Object> map) {
        return refundDao.getByOrderId(map);
    }
}

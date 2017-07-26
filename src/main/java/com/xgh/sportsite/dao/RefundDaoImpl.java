package com.xgh.sportsite.dao;

import com.xgh.sportsite.dao.read.IRefundDaoR;
import com.xgh.sportsite.dao.write.IRefundDaoW;
import com.xgh.sportsite.entity.Refund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/19.
 */
@Service("refundDao")
public class RefundDaoImpl implements IRefundDao {

    @Autowired
    protected IRefundDaoR refundDaoR;

    @Autowired
    protected IRefundDaoW refundDaoW;

    public Refund get(long id) {
        return refundDaoR.get(id);
    }

    public int insert(Refund refund) {
        return refundDaoW.insert(refund);
    }

    public int delete(long id) {
        return refundDaoW.delete(id);
    }

    public int update(Refund refund) {
        return refundDaoW.update(refund);
    }

    public List<Refund> getByOrderId(Map<String, Object> map) {
        return refundDaoR.getByOrderId(map);
    }

    public Refund getByBatchNo(String batchNo) {
        return refundDaoR.getByBatchNo(batchNo);
    }
}

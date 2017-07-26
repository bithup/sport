package com.xgh.sportsite.dao.read;

import com.xgh.sportsite.entity.Refund;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/19.
 */
public interface IRefundDaoR {

    public Refund get(long id);

    public List<Refund> getByOrderId(Map<String,Object> map);

    Refund getByBatchNo(String batchNo);
}

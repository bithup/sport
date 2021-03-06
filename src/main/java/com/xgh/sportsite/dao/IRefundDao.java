package com.xgh.sportsite.dao;

import com.xgh.sportsite.entity.Refund;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/19.
 */
public interface IRefundDao {

    public Refund get(long id);

    public int insert(Refund refund);

    public int delete(long id);

    public int update(Refund refund);

    public List<Refund> getByOrderId(Map<String,Object> map);

    Refund getByBatchNo(String batchNo);
}

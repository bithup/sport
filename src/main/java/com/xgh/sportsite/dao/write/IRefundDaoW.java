package com.xgh.sportsite.dao.write;

import com.xgh.sportsite.entity.Refund;

/**
 * Created by Administrator on 2016/12/19.
 */
public interface IRefundDaoW {

    public int insert(Refund refund);

    public int delete(long id);

    public int update(Refund refund);
}

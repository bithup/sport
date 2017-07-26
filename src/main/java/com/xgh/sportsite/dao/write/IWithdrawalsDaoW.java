package com.xgh.sportsite.dao.write;

import com.xgh.sportsite.entity.Withdrawals;

/**
 * Created by Administrator on 2016/12/20.
 */
public interface IWithdrawalsDaoW {

    public int delete(long id);


    public int insert(Withdrawals withdrawals);


    public int update(Withdrawals withdrawals);
}

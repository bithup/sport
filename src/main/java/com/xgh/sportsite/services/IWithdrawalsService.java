package com.xgh.sportsite.services;

import com.xgh.sportsite.entity.Withdrawals;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/20.
 */
public interface IWithdrawalsService {

    public Withdrawals get(long id);

    public int delete(long id);

    public int insert(Withdrawals withdrawals);

    public int update(Withdrawals withdrawals);

    public List<Map<String,Object>> getListPage(Map<String, Object> map);
}

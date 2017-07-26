package com.xgh.sportsite.dao.read;

import com.xgh.sportsite.entity.Withdrawals;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/20.
 */
public interface IWithdrawalsDaoR {

    public Withdrawals get(long id);

    public List<Map<String,Object>> getListPage(Map<String, Object> map);
}

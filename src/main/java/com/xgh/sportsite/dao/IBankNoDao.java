package com.xgh.sportsite.dao;

import com.xgh.sportsite.entity.BankNo;

import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/11/11.
 */
public interface IBankNoDao {


    public BankNo get(long id);

    public int add(BankNo bankNo);

    /**
     * 根据memberId查询所绑定的银行卡号
     *
     * @param memberId
     * @return
     */
    public List<Map<String, Object>> getBankNoByMemId(long memberId);


    /**
     * 根据银行卡号获取信息
     *
     * @param
     * @return
     */
    public BankNo getRepeatBankNo(Map<String, Object> map);

    public int update(BankNo bankNo);

    /**
     * 根据memberId获取默认银行卡
     */
    public BankNo getDefaultBankNo(long memberId);
}

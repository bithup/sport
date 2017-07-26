package com.xgh.sportsite.services;

import com.xgh.sportsite.entity.BankNo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/11/11.
 */
public interface IBankNoService {


    public BankNo get(long id);

    public int save(HttpServletRequest request, BankNo bankNo);

    /**
     * 根据银行卡号获取信息
     *
     * @param
     * @return
     */
    public BankNo getRepeatBankNo(Map<String, Object> map);

    /**
     * 根据memberId查询所绑定的银行卡号
     *
     * @param
     * @return
     */
    public Map<String, Object> getBankNoByMemId(HttpServletRequest request);

    /**
     * 根据memberId获取默认银行卡
     */
    public Map<String, Object> getDefaultBankNo(HttpServletRequest request);

    /**
     * 解除绑定银行卡
     */
    public Map<String, Object> unbindBanking(HttpServletRequest request);

}

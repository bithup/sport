package com.xgh.sportsite.services;

import com.xgh.sportsite.basic.BaseService;
import com.xgh.sportsite.dao.IBankNoDao;
import com.xgh.sportsite.entity.BankNo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by CQ on 2016/11/11.
 */
@Service("bankNoService")
public class BankNoServiceImpl extends BaseService implements IBankNoService {

    private static Logger logger = Logger.getLogger(BankNoServiceImpl.class);

    @Autowired
    protected IBankNoDao bankNoDao;


    public Map<String, Object> unbindBanking(HttpServletRequest request) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        String bankNoId = request.getParameter("bankNoId");

        BankNo bankNo = bankNoDao.get(Long.parseLong(bankNoId));
        if (bankNo != null) {
            bankNo.setStatus(-1);
            bankNo.setUpdateTime(new Date());
            int flg = bankNoDao.update(bankNo);
            if (flg > 0) {
                resultMap = getResultMap("1", "银行卡解绑成功!");
            } else
                resultMap = getResultMap("0", "银行卡解绑失败!");
        }
        return resultMap;
    }

    public BankNo get(long id) {
        return bankNoDao.get(id);
    }

    public int save(HttpServletRequest request, BankNo bankNo) {

        bankNo.setStatus(1);
        bankNo.setDefaultBindingBank(1);//默认绑定银行卡
        bankNo.setCreateTime(new Date());
        bankNo.setUpdateTime(new Date());

        String cardNo = bankNo.getCardNo();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cardNo", cardNo);
        map.put("memberId", bankNo.getMemberId());

        BankNo bankNo_ = bankNoDao.getRepeatBankNo(map);//根据银行卡号查询信息
        //如果此银行卡号存在就更新，反之则添加
        if (bankNo_ != null) {
            bankNo_.setUpdateTime(new Date());
            return bankNoDao.update(bankNo_);
        } else {
            logger.info("认证成功后进行插入...............");
            return bankNoDao.add(bankNo);
        }
    }

    public BankNo getRepeatBankNo(Map<String, Object> map) {
        return bankNoDao.getRepeatBankNo(map);
    }


    public Map<String, Object> getBankNoByMemId(HttpServletRequest request) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String memberId = request.getParameter("memberId");

        List<Map<String, Object>> bankNoList = bankNoDao.getBankNoByMemId(Long.parseLong(memberId));
        if (bankNoList != null && bankNoList.size() > 0) {
            resultMap = getResultMap("1", "绑定银行卡号列表显示成功!", bankNoList);
        } else {
            resultMap = getResultMap("0", "请绑定银行卡!");
        }
        return resultMap;
    }

    public Map<String, Object> getDefaultBankNo(HttpServletRequest request) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String bankNoId = request.getParameter("bankNoId");
        String memberId = request.getParameter("memberId");

        //根据会员id查询出已经绑定的默认银行卡,如果此会员存在已经绑定的银行卡,先把当前已经设置的银行卡默认为0，再把发送的银行卡设置为1
        BankNo bankNo1 = bankNoDao.getDefaultBankNo(Long.parseLong(memberId));
        if (bankNo1 != null) {
            bankNo1.setDefaultBindingBank(0);
            bankNo1.setUpdateTime(new Date());
            bankNoDao.update(bankNo1);
        }
        BankNo bankNo = bankNoDao.get(Long.parseLong(bankNoId));
        if (bankNo != null) {
            bankNo.setDefaultBindingBank(1);//把当前银行卡设置为默认银行卡
            bankNo.setUpdateTime(new Date());
            int flag = bankNoDao.update(bankNo);
            if (flag > 0) {
                resultMap = getResultMap("1", "绑定银行卡成功!");
            } else {
                resultMap = getResultMap("0", "绑定银行卡失败!");
            }
        }
        return resultMap;
    }
}
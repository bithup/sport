package com.xgh.sportsite.dao;

import com.xgh.sportsite.dao.read.IBankNoDaoR;
import com.xgh.sportsite.dao.write.IBankNoDaoW;
import com.xgh.sportsite.entity.BankNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/11/11.
 */
@Service("bankNoDao")
public class IBankNoDaoImpl implements IBankNoDao {


    @Autowired
    protected IBankNoDaoR bankNoDaoR;

    @Autowired
    protected IBankNoDaoW bankNoDaoW;

    public BankNo get(long id) {
        return bankNoDaoR.get(id);
    }

    public BankNo getRepeatBankNo(Map<String, Object> map) {
        return bankNoDaoR.getRepeatBankNo(map);
    }

    public int update(BankNo bankNo) {
        return bankNoDaoW.update(bankNo);
    }

    public int add(BankNo bankNo) {
        return bankNoDaoW.add(bankNo);
    }

    public List<Map<String, Object>> getBankNoByMemId(long memberId) {
        return bankNoDaoR.getBankNoByMemId(memberId);
    }

    public BankNo getDefaultBankNo(long memberId) {
        return bankNoDaoR.getDefaultBankNo(memberId);
    }
}

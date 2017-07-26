package com.xgh.sportsite.services;

import com.xgh.sportsite.dao.IWithdrawalsDao;
import com.xgh.sportsite.dao.write.IWithdrawalsDaoW;
import com.xgh.sportsite.entity.Withdrawals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/20.
 */
@Service("withdrawalsService")
public class WithdrawalsServiceImpl implements IWithdrawalsService {

    @Autowired
    protected IWithdrawalsDao withdrawalsDao;

    public Withdrawals get(long id) {
        return withdrawalsDao.get(id);
    }

    public int delete(long id) {
        return withdrawalsDao.delete(id);
    }

    public int insert(Withdrawals withdrawals) {
        return withdrawalsDao.insert(withdrawals);
    }

    public int update(Withdrawals withdrawals) {
        return withdrawalsDao.update(withdrawals);
    }

    public List<Map<String, Object>> getListPage(Map<String, Object> map) {
        return withdrawalsDao.getListPage(map);
    }
}

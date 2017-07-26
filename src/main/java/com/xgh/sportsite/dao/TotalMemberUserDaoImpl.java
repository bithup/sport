package com.xgh.sportsite.dao;

import com.xgh.sportsite.dao.readTR.ITotalMemebrUserDaoR;
import com.xgh.sportsite.dao.writeTW.ITotalMemebrUserDaoW;
import com.xgh.sportsite.entity.TotalMemebrUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by CQ on 2017/5/11.
 */
@Service("totalMemberUserDao")
public class TotalMemberUserDaoImpl implements ITotalMemberUserDao {


    @Autowired
    protected ITotalMemebrUserDaoR totalMemebrUserDaoR;

    @Autowired
    protected ITotalMemebrUserDaoW totalMemebrUserDaoW;


    public TotalMemebrUser login(String account) {
        return totalMemebrUserDaoR.login(account);
    }

    public TotalMemebrUser get(Long id) {
        return totalMemebrUserDaoR.get(id);
    }

    public int add(TotalMemebrUser record) {
        return totalMemebrUserDaoW.add(record);
    }

    public int update(TotalMemebrUser record) {
        return totalMemebrUserDaoW.update(record);
    }
}

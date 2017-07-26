package com.xgh.sportsite.services;

import com.xgh.sportsite.dao.ITotalMemberUserDao;
import com.xgh.sportsite.entity.TotalMemebrUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by CQ on 2017/5/11.
 */
@Service("totalMemberUserService")
public class TotalMemberUserServiceImpl implements ITotalMemberUserService {


    @Autowired
    protected ITotalMemberUserDao totalMemberUserDao;


    public TotalMemebrUser login(String account) {
        return totalMemberUserDao.login(account);
    }

    public TotalMemebrUser get(Long id) {
        return totalMemberUserDao.get(id);
    }

    public int add(TotalMemebrUser record) {
        return totalMemberUserDao.add(record);
    }

    public int update(TotalMemebrUser record) {
        return totalMemberUserDao.update(record);
    }
}

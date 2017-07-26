package com.xgh.sportsite.dao;

import com.xgh.sportsite.dao.read.IMemberUserDaoR;
import com.xgh.sportsite.dao.write.IMemberUserDaoW;
import com.xgh.sportsite.entity.MemberUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/6.
 */
@Service("memberUserDao")
public class MemberUserDaoImpl implements IMemberUserDao {

    @Autowired
    protected IMemberUserDaoR memberUserDaoR;

    @Autowired
    protected IMemberUserDaoW memberUserDaoW;

    public MemberUser get(long id) {
        return memberUserDaoR.get(id);
    }

    public int delete(long id) {
        return memberUserDaoW.delete(id);
    }

    public int insert(MemberUser memberUser) {
        return memberUserDaoW.insert(memberUser);
    }

    public int update(MemberUser memberUser) {
        return memberUserDaoW.update(memberUser);
    }

    public List<MemberUser> getRepeatAccount(String account) {
        return memberUserDaoR.checkRepeatAccount(account);
    }

    public MemberUser login(Map<String, Object> map) {
        return memberUserDaoR.login(map);
    }
}

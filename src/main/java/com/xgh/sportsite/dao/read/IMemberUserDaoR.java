package com.xgh.sportsite.dao.read;

import com.xgh.sportsite.entity.MemberUser;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/6.
 */
public interface IMemberUserDaoR {

    public MemberUser get(long id);

    public List<MemberUser> checkRepeatAccount(String account);

    /**
     * 登录
     *
     * @param map
     * @return
     */
    public MemberUser login(Map<String, Object> map);
}

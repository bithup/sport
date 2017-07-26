package com.xgh.sportsite.dao;

import com.xgh.sportsite.entity.MemberUser;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/6.
 */
public interface IMemberUserDao {

    public MemberUser get(long id);

    /**
     * 登录
     *
     * @param map
     * @return
     */
    public MemberUser login(Map<String, Object> map);

    public int delete(long id);

    public int insert(MemberUser memberUser);

    public int update(MemberUser memberUser);

    public List<MemberUser> getRepeatAccount(String account);
}

package com.xgh.sportsite.dao;

import com.xgh.sportsite.entity.TotalMemebrUser;

/**
 * Created by CQ on 2017/5/11.
 */
public interface ITotalMemberUserDao {


    /**
     * 查询（根据主键ID查询）
     **/
    public TotalMemebrUser get(Long id);

    /**
     * 登录
     *
     * @param account
     * @return
     */
    public TotalMemebrUser login(String account);

    /**
     * 添加
     **/
    public int add(TotalMemebrUser record);


    /**
     * 修改（根据主键ID修改）
     **/
    public int update(TotalMemebrUser record);
}

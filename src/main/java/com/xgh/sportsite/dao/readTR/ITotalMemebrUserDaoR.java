package com.xgh.sportsite.dao.readTR;


import com.xgh.sportsite.entity.TotalMemebrUser;

/**
 * TotalMemebrUserDaoR数据库操作接口类
 **/

public interface ITotalMemebrUserDaoR {


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


}
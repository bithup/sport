package com.xgh.sportsite.dao.writeTW;

import com.xgh.sportsite.entity.TotalMemebrUser;

/**
 * TotalMemebrUserDaoR数据库操作接口类
 **/

public interface ITotalMemebrUserDaoW {


    /**
     * 添加
     **/
    public int add(TotalMemebrUser record);


    /**
     * 修改（根据主键ID修改）
     **/
    public int update(TotalMemebrUser record);

}
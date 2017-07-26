package com.xgh.sportsite.dao.write;

import com.xgh.sportsite.entity.Coach;

/**
 * Created by CQ on 2016/12/8.
 */
public interface ICoachDaoW {
    /**
     * 教练注册时往教练表插入教练信息
     * @param coach
     * @return
     */
    int add(Coach coach);



    /**
     * 修改教练信息
     * @param coach
     * @return
     */
    int update(Coach coach);
}

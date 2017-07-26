package com.xgh.sportsite.dao.write;

import com.xgh.sportsite.entity.ChildVenueCutting;

/**
 * Created by Administrator on 2016/12/9.
 */
public interface IChildVenueCuttingDaoW {

    public int insert(ChildVenueCutting childVenueCutting);

    public int update(ChildVenueCutting childVenueCutting);

    public int delete(long id);
}

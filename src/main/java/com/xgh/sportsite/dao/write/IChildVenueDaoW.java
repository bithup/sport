package com.xgh.sportsite.dao.write;

import com.xgh.sportsite.entity.ChildVenue;

/**
 * Created by Administrator on 2016/12/7.
 */
public interface IChildVenueDaoW {

    public int add(ChildVenue childVenue);

    public int insert(ChildVenue childVenue);

    public int delete(long id);

    public int update(ChildVenue childVenue);
}

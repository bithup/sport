package com.xgh.sportsite.dao.write;

import com.xgh.sportsite.entity.Kinds;

/**
 * Created by Administrator on 2016/12/14.
 */
public interface IKindsDaoW {

    public int delete(long id);

    public int insert(Kinds kinds);

    public int update(Kinds kinds);
}

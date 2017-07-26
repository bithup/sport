package com.xgh.sportsite.dao.read;

import com.xgh.sportsite.entity.Kinds;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */
public interface IKindsDaoR {

    public Kinds get(long id);

    public List<Kinds> getIndexKinds();

    public List<Kinds> getIndexOtherKinds();

    public List<Kinds> getHeadKinds();
}

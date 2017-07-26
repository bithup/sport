package com.xgh.sportsite.dao;

import com.xgh.sportsite.entity.Kinds;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */
public interface IKindsDao {

    public Kinds get(long id);

    public List<Kinds> getIndexKinds();

    public int delete(long id);

    public int insert(Kinds kinds);

    public int update(Kinds kinds);

    public List<Kinds> getIndexOtherKinds();

    public List<Kinds> getHeadKinds();
}

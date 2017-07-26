package com.xgh.sportsite.services;

import com.xgh.sportsite.entity.Kinds;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/14.
 */
public interface IKindsService {

    public List<Kinds> getIndexKinds();

    public List<Map<String, Object>> getIndexOtherKinds();

    public List<Kinds> getHeadKinds();

    public Kinds get(long id);
}

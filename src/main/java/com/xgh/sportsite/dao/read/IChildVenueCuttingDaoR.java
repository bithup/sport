package com.xgh.sportsite.dao.read;

import com.sun.javafx.collections.MappingChange;
import com.xgh.sportsite.entity.ChildVenueCutting;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/9.
 */
public interface IChildVenueCuttingDaoR {

    public ChildVenueCutting get(long id);

    public List<Map<String,Object>> getByVenueId(Map<String,Object> map);

    public List<Map<String,Object>> getByOrderId(Map<String,Object> map);

    /**
     * 获取已经付过款的场次
     * @param map
     * @return
     */
    public String getAlreadyPay(Map<String,Object> map);
}

package com.xgh.sportsite.dao;

import com.xgh.sportsite.entity.ChildVenue;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/8.
 */
public interface IChildVenueDao {

    public ChildVenue get(long id);

    public int add(ChildVenue childVenue);

    public int update(ChildVenue childVenue);

    public List<Map<String,Object>> getRecommendVenue(Map<String,Object> map);

    public List<Map<String,Object>> getListPage(Map<String,Object> map);

    public Map<String,Object> getDetail(long id);

    public List<Map<String,Object>> getVenueByPid(Map<String,Object> map);

    public List<Map<String,Object>> getIndexSearch(Map<String,Object> map);

    public List<Map<String, Object>> getChildVenue(Map<String, Object> map);
}

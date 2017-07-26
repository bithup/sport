package com.xgh.sportsite.services;

import com.xgh.sportsite.entity.ChildVenue;
import com.xgh.sportsite.entity.Venue;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/8.
 */
public interface IChildVanueService {

    public ChildVenue get(long id);

    public List<Map<String,Object>> getRecommendVenue(HttpServletRequest request);

    public List<Map<String,Object>> getListPage(Map<String,Object> map);

    public Map<String,Object> getDetail(long id);

    public List<Map<String,Object>> getVenueByPid(Map<String,Object> map);

    public List<Map<String,Object>> getIndexSearch(Map<String,Object> map);

    int update(ChildVenue childVenue);

    public int addChildVenue(HttpServletRequest request,ChildVenue childVenue);

    public int updateChildVenue(HttpServletRequest request,ChildVenue childVenue);

    public int deleteChildVenue(HttpServletRequest request);

    public List<Map<String,Object>> getChildVenue(Map<String, Object> map);
}

package com.xgh.sportsite.dao;

import com.sun.tracing.dtrace.Attributes;
import com.xgh.sportsite.dao.read.IChildVenueDaoR;
import com.xgh.sportsite.dao.write.IChildVenueDaoW;
import com.xgh.sportsite.entity.ChildVenue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/8.
 */
@Service("childVenueDao")
public class ChildVenueDaoImpl implements IChildVenueDao {

    @Autowired
    protected IChildVenueDaoR childVenueDaoR;

    @Autowired
    protected IChildVenueDaoW childVenueDaoW;

    public ChildVenue get(long id) {
        return childVenueDaoR.get(id);
    }

    public int add(ChildVenue childVenue){
        return childVenueDaoW.add(childVenue);
    }

    public int update(ChildVenue childVenue){
        return childVenueDaoW.update(childVenue);
    }

    public List<Map<String, Object>> getRecommendVenue(Map<String, Object> map) {
        return childVenueDaoR.getRecommendVenue(map);
    }

    public List<Map<String, Object>> getListPage(Map<String, Object> map) {
        return childVenueDaoR.getListPage(map);
    }

    public Map<String, Object> getDetail(long id) {
        return childVenueDaoR.getDetail(id);
    }

    public List<Map<String, Object>> getVenueByPid(Map<String, Object> map) {
        return childVenueDaoR.getVenueByPid(map);
    }

    public List<Map<String,Object>> getIndexSearch(Map<String,Object> map){
        return childVenueDaoR.getIndexSearch(map);
    }

    public List<Map<String, Object>> getChildVenue(Map<String, Object> map){
        return  childVenueDaoR.getChildVenue(map);
    }
}

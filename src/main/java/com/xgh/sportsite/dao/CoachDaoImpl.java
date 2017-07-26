package com.xgh.sportsite.dao;

import com.xgh.sportsite.dao.read.ICoachDaoR;
import com.xgh.sportsite.dao.write.ICoachDaoW;
import com.xgh.sportsite.entity.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/12/8.
 */
@Service("coachDao")
public class CoachDaoImpl implements ICoachDao {

    @Autowired
    protected ICoachDaoR coachDaoR;

    @Autowired
    protected ICoachDaoW coachDaoW;

    public Map<String, Object> get(long id) {
        return coachDaoR.get(id);
    }

    public List<Map<String, Object>> getCoachListBySportId(Map<String, Object> map) {
        return coachDaoR.getCoachListBySportId(map);
    }

    public List<Map<String, Object>> getCoachIndexResearch(Map<String, Object> map) {
        return coachDaoR.getCoachIndexResearch(map );
    }

    public int add(Coach coach) {
        return coachDaoW.add(coach);
    }

    public List<Map<String, Object>> getRecommendCoach(Map<String, Object> map) {
        return coachDaoR.getRecommendCoach(map);
    }

    /**
     * 条件分页查询
     * @param map
     * @return
     */
    public List<Map<String, Object>> getListPage(Map<String, Object> map) {
        return coachDaoR.getListPage(map);
    }


    /**
     * 根据电话号查教练
     * @param telPhone
     * @return
     */
    public List<Coach> getCoachByPhone(String telPhone) {
        return coachDaoR.getCoachByPhone(telPhone);
    }

    public Coach getCoachByCoachId(long coachId) {
        return coachDaoR.getCoachByCoachId(coachId);
    }

    public int update(Coach coach) {
        return coachDaoW.update(coach);
    }

}

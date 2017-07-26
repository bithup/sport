package com.xgh.sportsite.services;

import com.xgh.sportsite.dao.ICoachDao;
import com.xgh.sportsite.entity.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/12/8.
 */
@Service("coachService")
public class CoachServiceImpl implements ICoachService {

    @Autowired
    protected ICoachDao coachDao;


    public Map<String, Object> get(long id) {
        return coachDao.get(id);
    }


    public List<Map<String, Object>> getCoachListBySportId(Map<String, Object> map) {
        return coachDao.getCoachListBySportId(map);
    }

    public List<Map<String, Object>> getCoachIndexResearch(Map<String, Object> map) {
        return coachDao.getCoachIndexResearch(map);
    }

    public List<Map<String, Object>> getRecommendCoach(Map<String, Object> map) {
        return coachDao.getRecommendCoach(map);
    }

    public List<Map<String, Object>> getListPage(Map<String, Object> map) {
        return coachDao.getListPage(map);
    }

    public List<Coach> getCoachByPhone(String telPhone) {
        return coachDao.getCoachByPhone(telPhone);
    }

    public Coach getCoachByCoachId(long coachId) {
        return coachDao.getCoachByCoachId(coachId);
    }
    public int update(Coach coach) {
        return coachDao.update(coach);
    }
}

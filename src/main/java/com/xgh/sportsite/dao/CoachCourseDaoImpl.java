package com.xgh.sportsite.dao;

import com.xgh.sportsite.dao.read.ICoachCourseDaoR;
import com.xgh.sportsite.dao.write.ICoachCourseDaoW;
import com.xgh.sportsite.entity.CoachCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/12/8.
 */
@Service("coachCourseDao")
public class CoachCourseDaoImpl implements ICoachCourseDao {


    @Autowired
    protected ICoachCourseDaoR coachCourseDaoR;

    @Autowired
    protected ICoachCourseDaoW coachCourseDaoW;

    public CoachCourse get(long id) {
        return coachCourseDaoR.get(id);
    }

    public List<Map<String, Object>> getCourseListByMemId(Map<String, Object> map) {
        return coachCourseDaoR.getCourseListByMemId(map);
    }
    public int add(CoachCourse coachCourse) {
        return coachCourseDaoW.add(coachCourse);
    }

    public int update(CoachCourse coachCourse) {
        return coachCourseDaoW.update(coachCourse);
    }
}

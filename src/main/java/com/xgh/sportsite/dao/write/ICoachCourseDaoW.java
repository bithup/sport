package com.xgh.sportsite.dao.write;

import com.xgh.sportsite.entity.CoachCourse;

import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/12/8.
 */
public interface ICoachCourseDaoW {

    /**
     * 添加课程
     * @param coachCourse
     * @return
     */
    int add(CoachCourse coachCourse);

    /**
     * 修改课程
     * @param coachCourse
     * @return
     */
    int update(CoachCourse coachCourse);



}

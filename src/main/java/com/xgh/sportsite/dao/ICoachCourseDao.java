package com.xgh.sportsite.dao;

import com.xgh.sportsite.entity.CoachCourse;

import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/12/8.
 */
public interface ICoachCourseDao {



    /**
     * 根据id查询教练课程
     *
     * @param id
     * @return
     */
    public CoachCourse get(long id);


    /**
     *
     * 根据教练id接收课程列表
     * @param map
     * @return
     */
    public List<Map<String, Object>> getCourseListByMemId(Map<String, Object> map);

    int add(CoachCourse coachCourse);

    /**
     * 修改课程
     * @param coachCourse
     * @return
     */
    int update(CoachCourse coachCourse);


}

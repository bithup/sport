package com.xgh.sportsite.services;

import com.xgh.sportsite.entity.CoachCourse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/12/8.
 */
public interface ICoachCourseService {


    /**
     *
     * 根据教练id接收课程列表
     * @param map
     * @return
     */
    public List<Map<String, Object>> getCourseListByMemId(Map<String, Object> map);

    public CoachCourse get(long id);

    public Map<String,Object> sava(HttpServletRequest request, CoachCourse coachCourse);

    public int update(CoachCourse coachCourse);

}

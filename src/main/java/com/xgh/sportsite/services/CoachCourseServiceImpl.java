package com.xgh.sportsite.services;

import com.xgh.sportsite.basic.BaseService;
import com.xgh.sportsite.dao.ICoachCourseDao;
import com.xgh.sportsite.entity.CoachCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/12/8.
 */
@Service("coachCourseService")
public class CoachCourseServiceImpl extends BaseService implements ICoachCourseService {


    @Autowired
    protected ICoachCourseDao coachCourseDao;
    public List<Map<String, Object>> getCourseListByMemId(Map<String, Object> map) {
        return coachCourseDao.getCourseListByMemId(map);
    }
    public Map<String, Object> sava(HttpServletRequest request, CoachCourse coachCourse) {
        Map<String,Object> resultMap = new HashMap<String, Object>();
        String id = request.getParameter("id");
        int flag = 0;
        if(null!=id&&!"0".equals(id)){
            long id_ = Long.parseLong(id);
            CoachCourse coachCourse1 = coachCourseDao.get(id_);
            Date date = new Date();
            coachCourse.setId(id_);
            coachCourse.setCreateDate(coachCourse1.getCreateDate());
            coachCourse.setUpdateDate(date);
            coachCourse.setStatus(1);
            flag = coachCourseDao.update(coachCourse);
        }else{
            Date date = new Date();
            coachCourse.setCreateDate(date);
            coachCourse.setUpdateDate(date);
            coachCourse.setStatus(1);
            flag = coachCourseDao.add(coachCourse);
        }
        if(flag>0){
            resultMap = getResultMap("1","保存成功");
        }else{
            resultMap = getResultMap("0","保存失败，请重试");
        }
        return resultMap;
    }
    public int update(CoachCourse coachCourse) {
        return coachCourseDao.update(coachCourse);
    }
    public CoachCourse get(long id) {
        return coachCourseDao.get(id);
    }

}

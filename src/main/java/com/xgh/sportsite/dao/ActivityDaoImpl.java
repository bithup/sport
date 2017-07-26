package com.xgh.sportsite.dao;

import com.xgh.sportsite.dao.read.IActivityDaoR;
import com.xgh.sportsite.dao.write.IActivityDaoW;
import com.xgh.sportsite.entity.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/12/12.
 */
@Service("ActivityDao")
public class ActivityDaoImpl implements IActivityDao {


    @Autowired
    protected IActivityDaoR activityDaoR;

    @Autowired
    protected IActivityDaoW activityDaoW;

    public int add(Activity activity) {
        return activityDaoW.add(activity);
    }

    public int update(Activity activity) {
        return activityDaoW.update(activity);
    }

    public Activity get(long id) {
        return activityDaoR.get(id);
    }


    public List<Map<String, Object>> getActivityBySportId(Map<String, Object> map) {
        return activityDaoR.getActivityBySportId(map);
    }

    public List<Map<String, Object>> getActivityIndexResearch(Map<String, Object> map) {
        return activityDaoR.getActivityIndexResearch(map);
    }

    public List<Map<String, Object>> getRecommendActivity(Map<String, Object> map) {
        return activityDaoR.getRecommendActivity(map);
    }

    public List<Map<String, Object>> getListPage(Map<String, Object> map) {
        return activityDaoR.getListPage(map);
    }

    public List<Map<String, Object>> getPublishActivity(Map<String, Object> map) {
        return activityDaoR.getPublishActivity(map);
    }
}

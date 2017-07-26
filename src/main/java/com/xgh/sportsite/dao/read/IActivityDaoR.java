package com.xgh.sportsite.dao.read;

import com.xgh.sportsite.entity.Activity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/12/12.
 */

public interface IActivityDaoR {

    /**
     * 根据主键id查询活动
     *
     * @param id
     * @return
     */
    public Activity get(long id);









    /**
     * 根据运动类型查询活动
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> getActivityBySportId(Map<String, Object> map);

    /**
     * 根据活动名称查询活动
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> getActivityIndexResearch(Map<String, Object> map);

    /**
     * 查询热门活动
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> getRecommendActivity(Map<String, Object> map);

    /**
     * 条件分页查询
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> getListPage(Map<String, Object> map);

    /**
     * 查询自己发布的活动
      * @param map
     * @return
     */
    public List<Map<String, Object>> getPublishActivity(Map<String, Object> map);


}

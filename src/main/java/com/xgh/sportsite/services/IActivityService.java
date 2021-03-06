package com.xgh.sportsite.services;

import com.xgh.sportsite.entity.Activity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/12/12.
 */
public interface IActivityService {


    public Map<String, Object> getActivitySignDetail(HttpServletRequest request);

    /**
     * save
     */
    public int save(HttpServletRequest request, Activity activity);


    /**
     * 取消会员发布的活动
     */
    public Map<String, Object> cancel(HttpServletRequest request);


    /**
     * 根据主键id查询活动
     *
     * @param id
     * @return
     */
    public Activity get(long id);


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
     *
     * @param map
     * @return
     */
    public Map<String, Object> getPublishActivity(HttpServletRequest request, Map<String, Object> map);

}

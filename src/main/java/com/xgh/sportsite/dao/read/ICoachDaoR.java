package com.xgh.sportsite.dao.read;

import com.xgh.sportsite.entity.Coach;

import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/12/8.
 */
public interface ICoachDaoR {

    /**
     * 根据id查询教练
     *
     * @param id
     * @return
     */
    public Map<String, Object> get(long id);

    /**
     * 根据运动类型查询教练列表
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> getCoachListBySportId(Map<String, Object> map);


    /**
     * 教练搜索
     */
    public List<Map<String, Object>> getCoachIndexResearch(Map<String, Object> map);

    /**
     * 首页热门教练
     * @param map
     * @return
     */
    public List<Map<String,Object>> getRecommendCoach(Map<String, Object> map);

    /**
     * 条件分页查询
     * @param map
     * @return
     */
    public List<Map<String,Object>> getListPage(Map<String, Object> map);

    /**
     * 根据电话号查教练
     * @param telPhone
     * @return
     */
    public List<Coach> getCoachByPhone(String telPhone);
    /**
     * 根据用户id查询教练信息
     * @param coachId
     * @return
     */
    public Coach getCoachByCoachId(long coachId);

}

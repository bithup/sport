package com.xgh.sportsite.dao.read;

import com.xgh.sportsite.entity.House;

import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/12/15.
 */
public interface IHouseDaoR {


    public House get(long id);

    /**
     * 场馆收藏列表查询
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> getVenueHouseList(Map<String, Object> map);

    /**
     * 教练收藏列表查询
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> getCoachHouseList(Map<String, Object> map);

    /**
     * 活动收藏列表查询
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> getActivityHouseList(Map<String, Object> map);

    /**
     * 收藏列表查询
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> getHouseListObject(Map<String, Object> map);


    public List<House> checkHouseExist(Map<String, Object> map);

    public List<Map<String, Object>> getHouseList(Map<String, Object> map);
}

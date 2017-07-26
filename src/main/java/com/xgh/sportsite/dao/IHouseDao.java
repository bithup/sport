package com.xgh.sportsite.dao;

import com.xgh.sportsite.entity.House;

import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/12/15.
 */
public interface IHouseDao {


    /**
     * 批量更新
     *
     * @param list
     * @return
     */
    public int batchUpdateList(List<Map<String, Object>> list);

    /*public int batchUpdateByIdList(List<Integer> list);*/

    public int batchUpdateByIdList(List<String> list);

    public int batchUpdateMap(Map<String, Object> map);


    public int batchUpdateWithArray(String array[]);
    /**
     * 收藏列表查询
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> getHouseListObject(Map<String, Object> map);

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
    public List<Map<String, Object>> getHouseList(Map<String, Object> map);


    public int add(House house);

    public int update(House house);

    public List<House> checkHouseExist(Map<String, Object> map);


}

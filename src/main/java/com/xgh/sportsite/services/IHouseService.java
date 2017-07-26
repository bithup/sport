package com.xgh.sportsite.services;

import com.xgh.sportsite.entity.House;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/12/15.
 */
public interface IHouseService {


    /**
     * 批量更新
     *
     * @param list
     * @return
     */
    public int batchUpdateList(List<Map<String, Object>> list);

    /*public int batchUpdateByIdList(List<Integer> list);*/

    public Map<String, Object> batchUpdateByIdList(HttpServletRequest request);

    public Map<String, Object> batchUpdateMap(HttpServletRequest request);


    public House get(long id);

    /**
     * 收藏列表查询
     *
     * @param map
     * @return
     */
    public Map<String, Object> getHouseList(HttpServletRequest request, Map<String, Object> map);

    public Map<String, Object> save(HttpServletRequest request, House house);

    public Map<String, Object> update(HttpServletRequest request, House house);

    public List<House> checkHouseExist(Map<String, Object> map);

}

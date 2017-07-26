package com.xgh.sportsite.dao.write;

import com.xgh.sportsite.entity.House;

import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/12/15.
 */
public interface IHouseDaoW {


    public int add(House house);

    public int update(House house);

    /**
     * 批量更新
     *
     * @param list
     * @return
     */
    public int batchUpdateList(List<Map<String, Object>> list);

    public int batchUpdateMap(Map<String, Object> map);

    public int batchUpdateWithArray(String array[]);

    /*public int batchUpdateByIdList(List<Integer> list);*/

    public int batchUpdateByIdList(List<String> list);

}

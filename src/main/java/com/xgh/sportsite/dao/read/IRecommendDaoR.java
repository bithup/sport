package com.xgh.sportsite.dao.read;

import com.xgh.sportsite.entity.Recommend;

import java.util.List;
import java.util.Map;

/**
 * Created by xiaowenbo on 2016/3/28 0028.
 * 创意 2.挑毛病 3.吐槽
 */
public interface IRecommendDaoR {
    /**
     * 获取列表数据
     * @param postMap
     * @return
     */
    public List<Map<String,Object>> getRecommendList(Map<String, Object> postMap);


    public Recommend get(long id);
}

package com.xgh.sportsite.dao;

import com.xgh.sportsite.entity.Recommend;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/28 0028.
 */
public interface IRecommendDao {
    /**
     * 获取列表数据
     * @param postMap
     * @return
     */
    public List<Map<String,Object>> getRecommendList(Map<String, Object> postMap);
    /**
     * 添加反馈
     * @param recommend
     * @return
     */
    public Integer add(Recommend recommend);


    public Recommend get(long id);


    public int delete(long id);


    public int update(Recommend recommend);
}

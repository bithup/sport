package com.xgh.sportsite.dao;

import com.xgh.sportsite.dao.read.IRecommendDaoR;
import com.xgh.sportsite.dao.write.IRecommendDaoW;
import com.xgh.sportsite.entity.Recommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by xiaowenbo on 2016/3/28 0028.
 *  1.创意 2.挑毛病 3.吐槽
 */
@Service("recommendDao")
public class RecommendDaoImpl implements IRecommendDao  {
    @Autowired
    protected IRecommendDaoR recommendDaoR;
    @Autowired
    protected IRecommendDaoW recommendDaoW;

    /**
     * 获取列表
     * @param postMap
     * @return
     */
    public List<Map<String,Object>> getRecommendList(Map<String, Object> postMap) {

        return this.recommendDaoR.getRecommendList(postMap);
    }

    public Recommend get(long id) {
        return null;
    }

    public int delete(long id) {
        return 0;
    }

    public int update(Recommend recommend) {
        return 0;
    }

    /**
     * 添加操作
     * @param recommend
     * @return
     */
    public Integer add(Recommend recommend) {

        return    recommendDaoW.add(recommend);
    }
}

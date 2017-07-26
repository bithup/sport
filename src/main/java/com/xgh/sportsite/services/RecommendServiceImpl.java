package com.xgh.sportsite.services;

import com.xgh.sportsite.dao.IRecommendDao;
import com.xgh.sportsite.dao.write.IRecommendDaoW;
import com.xgh.sportsite.entity.Recommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaoewnbo on 2016/3/28 0028.
 * 反馈
 */
@Service("recommendService")
public class RecommendServiceImpl implements IRecommendService {
    @Autowired
    protected IRecommendDao recommendDaoR;
    @Autowired
    protected IRecommendDaoW recommendDaoW;


    public List<Map<String, Object>> getRecommendList(Map<String, Object> postMap) {

        return recommendDaoR.getRecommendList(postMap);
    }

    public Integer add(Recommend recommend) {
        Date date=new Date();
        recommend.setCreateDate(date);
        recommend.setUpdateDate(date);
        recommend.setStatus(1);
        return recommendDaoW.add(recommend);
    }
}

package com.xgh.sportsite.services;

import com.xgh.sportsite.dao.IKindsDao;
import com.xgh.sportsite.entity.Kinds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/14.
 */
@Service("kindsService")
public class KindsServiceImpl implements IKindsService {

    @Autowired
    protected IKindsDao kindsDao;

    /**
     * 获取首页运动类型
     * @return
     */
    public List<Kinds> getIndexKinds() {
        return kindsDao.getIndexKinds();
    }

    public List<Map<String, Object>> getIndexOtherKinds() {

        List<Kinds> list = kindsDao.getIndexOtherKinds();
        List<Map<String,Object>> resultList = new ArrayList<Map<String, Object>>();
        Map<String,Object> kindsMap = null;
        List<Kinds> childList = null;
        for(Kinds kinds:list){
            kindsMap = new HashMap<String, Object>();
            if(kinds.getParentId()==0){
                kindsMap.put("parentKinds",kinds);
                childList = new ArrayList<Kinds>();
                for(Kinds kinds1:list){
                    if(kinds1.getParentId().longValue()==kinds.getId().longValue()){
                        childList.add(kinds1);
                    }
                }
                kindsMap.put("childList",childList);
                resultList.add(kindsMap);
            }
        }
        return resultList;
    }

    public List<Kinds> getHeadKinds() {
        return kindsDao.getHeadKinds();
    }

    public Kinds get(long id){
        return kindsDao.get(id);
    }
}

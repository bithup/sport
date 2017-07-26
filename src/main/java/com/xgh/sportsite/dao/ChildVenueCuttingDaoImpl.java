package com.xgh.sportsite.dao;

import com.xgh.sportsite.dao.read.IChildVenueCuttingDaoR;
import com.xgh.sportsite.dao.write.IChildVenueCuttingDaoW;
import com.xgh.sportsite.entity.ChildVenueCutting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/9.
 */
@Service("childVenueCuttingDao")
public class ChildVenueCuttingDaoImpl implements IChildVenueCuttingDao {

    @Autowired
    protected IChildVenueCuttingDaoR childVenueCuttingDaoR;

    @Autowired
    protected IChildVenueCuttingDaoW childVenueCuttingDaoW;

    public ChildVenueCutting get(long id) {
        return childVenueCuttingDaoR.get(id);
    }

    public List<Map<String, Object>> getByVenueId(Map<String, Object> map) {
        return childVenueCuttingDaoR.getByVenueId(map);
    }

    public int insert(ChildVenueCutting childVenueCutting){
        return childVenueCuttingDaoW.insert(childVenueCutting);
    }

    public List<Map<String, Object>> getByOrderId(Map<String, Object> map) {
        return childVenueCuttingDaoR.getByOrderId(map);
    }

    public String getAlreadyPay(Map<String, Object> map) {
        return childVenueCuttingDaoR.getAlreadyPay(map);
    }
}

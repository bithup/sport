package com.xgh.sportsite.services;

import com.xgh.sportsite.dao.IChildVenueCuttingDao;
import com.xgh.sportsite.entity.ChildVenueCutting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.interceptor.AroundInvoke;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/9.
 */
@Service("childVenueCuttingService")
public class ChildVenueCuttingServiceImpl implements IChildVenueCuttingService {

    @Autowired
    protected IChildVenueCuttingDao childVenueCuttingDao;

    public ChildVenueCutting get(long id) {
        return childVenueCuttingDao.get(id);
    }

    public List<Map<String, Object>> getByVenueId(Map<String, Object> map) {
        return childVenueCuttingDao.getByVenueId(map);
    }

    public List<Map<String, Object>> getByOrderId(Map<String, Object> map) {
        return childVenueCuttingDao.getByOrderId(map);
    }

    public String getAlreadyPay(Map<String, Object> map) {
        return childVenueCuttingDao.getAlreadyPay(map);
    }
}

package com.xgh.sportsite.dao;

import com.xgh.sportsite.dao.read.IHouseDaoR;
import com.xgh.sportsite.dao.write.IHouseDaoW;
import com.xgh.sportsite.entity.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/12/15.
 */
@Service("houseDao")
public class HouseDaoImpl implements IHouseDao {


    @Autowired
    protected IHouseDaoR houseDaoR;

    @Autowired
    protected IHouseDaoW houseDaoW;

    public House get(long id) {
        return houseDaoR.get(id);
    }

    public List<Map<String, Object>> getHouseList(Map<String, Object> map) {
        return houseDaoR.getHouseList(map);
    }

    public int batchUpdateList(List<Map<String, Object>> list) {
        return houseDaoW.batchUpdateList(list);
    }

    public int batchUpdateByIdList(List<String> list) {
        return houseDaoW.batchUpdateByIdList(list);
    }

    public int batchUpdateMap(Map<String, Object> map) {
        return houseDaoW.batchUpdateMap(map);
    }

    public List<Map<String, Object>> getHouseListObject(Map<String, Object> map) {
        return houseDaoR.getHouseListObject(map);
    }

    public int batchUpdateWithArray(String array[]) {
        return houseDaoW.batchUpdateWithArray(array);
    }

    public int add(House house) {
        return houseDaoW.add(house);
    }

    public int update(House house) {
        return houseDaoW.update(house);
    }

    public List<House> checkHouseExist(Map<String, Object> map) {
        return houseDaoR.checkHouseExist(map);

    }
    public List<Map<String, Object>> getVenueHouseList(Map<String, Object> map) {
        return houseDaoR.getVenueHouseList(map);
    }

    public List<Map<String, Object>> getCoachHouseList(Map<String, Object> map) {
        return houseDaoR.getCoachHouseList(map);
    }

    public List<Map<String, Object>> getActivityHouseList(Map<String, Object> map) {
        return houseDaoR.getActivityHouseList(map);
    }
}

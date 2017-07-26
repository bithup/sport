package com.xgh.sportsite.dao;


import com.xgh.sportsite.dao.read.IZoneDaoR;
import com.xgh.sportsite.dao.write.IZoneDaoW;
import com.xgh.sportsite.entity.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ZoneDao Impl
 *
 * @author duanxg
 * @time:2016-02-18 11:53:33
 * @Email:
 */
@Service("zoneDao")
public class ZoneDaoImpl implements IZoneDao {

    @Autowired
    protected IZoneDaoR zoneDaoR;

    @Autowired
    protected IZoneDaoW zoneDaoW;

    /**
     * add
     */
    public int add(Zone zone) {
        return zoneDaoW.add(zone);
    }

    /**
     * addBatch
     */
    public int addBatch(List<Zone> list) {
        return zoneDaoW.addBatch(list);
    }

    /**
     * update
     */
    public int update(Zone zone) {
        return zoneDaoW.update(zone);
    }

    /**
     * delete
     */
    public int deleteById(long id) {
        return zoneDaoW.deleteById(id);
    }

    /**
     * get
     *
     * @return
     */
    public Zone get(long id) {
        return zoneDaoR.get(id);
    }


    /**
     * getList
     *
     * @return
     */
    public List<Zone> getList(Map<String, Object> map) {
        return zoneDaoR.getList(map);
    }


    /**
     * getListPage
     * <p/>
     * page,pagesize,key
     *
     * @return
     */
    public List<Zone> getListPage(Map<String, Object> map) {
        return zoneDaoR.getListPage(map);
    }

    /**
     * getRows
     *
     * @param map
     * @return id desc,name ,date asc
     */
    public long getRows(Map<String, Object> map) {
        return zoneDaoR.getRows(map);
    }

    /**
     * 获取zone信息
     *
     * @param map
     * @return
     */
    public List<Zone> getZones(Map<String, Object> map) {
        return zoneDaoR.getZones(map);
    }

    /**
     * 根据市code获取区列表
     * @param map
     * @return
     */
    public List<Zone> getAreaInfoByPcode(Map<String, Object> map) {
        return this.zoneDaoR.getAreaInfoByPcode(map);
    }


    public Zone getNameByPid(Map<String, Object> map) {
        return zoneDaoR.getNameByPid(map);
    }

    public Zone getIdByName(Map<String, Object> map) {
        return zoneDaoR.getIdByName(map);
    }

    public Zone getIdByNames(Map<String, Object> map) {
        return zoneDaoR.getIdByNames(map);
    }
    public Zone getZoneByCode(String code) {
        return this.zoneDaoR.getZoneByCode(code);
    }
}
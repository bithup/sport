package com.xgh.sportsite.dao.read;

import com.xgh.sportsite.entity.Zone;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * ZoneDao read
 *
 * @author duanxg
 * @time:2016-02-18 11:53:33
 * @Email:
 */
@Component
public interface IZoneDaoR {

    /**
     * get
     *
     * @return
     */
    public Zone get(long id);

    /**
     * getList
     *
     * @return
     */
    public List<Zone> getList(Map<String, Object> map);


    /**
     * getListPage
     * <p/>
     * page,pagesize,key
     *
     * @return
     */
    public List<Zone> getListPage(Map<String, Object> map);

    /**
     * getRows
     *
     * @param map
     * @return id desc,name ,date asc
     */
    public long getRows(Map<String, Object> map);

    /**
     * 获取zone信息
     *
     * @param map
     * @return
     */
    public List<Zone> getZones(Map<String, Object> map);

    /**
     * 根据市code获取区列表
     * @param map
     * @return
     */
    public List<Zone> getAreaInfoByPcode(Map<String, Object> map);


    public Zone getNameByPid(Map<String, Object> map);


    public Zone getIdByName(Map<String, Object> map);

    public Zone getIdByNames(Map<String, Object> map);

    Zone getZoneByCode(String var1);


}
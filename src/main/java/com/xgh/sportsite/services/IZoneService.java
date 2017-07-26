package com.xgh.sportsite.services;


import com.xgh.sportsite.entity.Zone;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * ZoneService
 *
 * @author duanxg
 * @time:2016-02-18 11:53:33
 * @Email:
 */
public interface IZoneService {

    public int add(Zone zone);

    public int update(Zone zone);

    public int save(Zone zone);

    public int delete(long id);

    public Zone get(long id);

    public List<Zone> getList(Map<String, Object> map);

    public Map<String, Object> getGridList(HttpServletRequest request);

    /**
     * 通过区域名称获取zone
     *
     * @param request
     * @return
     */
    public Zone getZoneByName(HttpServletRequest request);
    /**
     * 根据市code获取区列表
     * @param map
     * @return
     */
    public List<Zone> getAreaInfoByPcode(Map<String, Object> map);

    /**
     *
     */
    public Zone getNameByPid(Map<String, Object> map);

    public Zone getIdByName(Map<String, Object> map);
    /**
     * 获取zone信息
     *
     * @param map
     * @return
     */
    public List<Zone> getZones(Map<String, Object> map);

    public Zone getIdByNames(Map<String, Object> map);

    Zone getZoneByCode(String var1);

}

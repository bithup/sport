package com.xgh.sportsite.services;


import com.xgh.sportsite.dao.IZoneDao;
import com.xgh.sportsite.entity.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * ZoneService Impl
 *
 * @author duanxg
 *
 * @time:2016-02-18 11:53:33
 *
 * @Email:
 */
@Service("zoneService")
public class ZoneServiceImpl implements IZoneService{


	@Autowired
	protected IZoneDao zoneDao;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param zone
	 *
	 */
	public int add(Zone zone) {
		// TODO Auto-generated method stub

		return zoneDao.add(zone);
	}

	public int update(Zone zone) {
		// TODO Auto-generated method stub
		return zoneDao.update(zone);
	}
	
	public int save(Zone zone){
		//此处认为shop不为空，进行操作，是否为空的判断在controller中进行
		if(zone!=null && zone.getId()<1){
			//该处认为是添加操作
			Date date = new Date();

			zone.setCreateDate(date);
			zone.setUpdateDate(date);
			zone.setStatus(1);

			return add(zone);
		}else{
			Zone zone2 = get(zone.getId());

			zone2.setUpdateDate(new Date());
			return update(zone);
		}
	}
	
	public int delete(long id) {
		// TODO Auto-generated method stub
		return zoneDao.deleteById(id);
	}

	public Zone get(long id) {
		// TODO Auto-generated method stub
		return zoneDao.get(id);
	}

	public List<Zone> getList(Map<String,Object> map){
		
		return zoneDao.getList(map);
	}
	
	public Map<String,Object> getGridList(HttpServletRequest request){

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");

		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("instId",getCurrentInstId(request));
		//map.put("unitId", getCurrentUnitId(request));
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));

		Map<String, Object> gridMap = new HashMap<String, Object>();

		List<Zone> dataList = zoneDao.getListPage(map);
		if (dataList == null) {
			dataList = new ArrayList<Zone>();
		}
		gridMap.put("Rows", dataList);
		gridMap.put("Total", zoneDao.getRows(map));
		return gridMap;
	}

	/**
	 * 通过区域名称获取zone
	 * @param request
	 * @return
	 */
	public Zone getZoneByName(HttpServletRequest request){
		String zoneName = request.getParameter("zoneName");

		Map<String,Object> zoneMap = new HashMap<String, Object>();
		zoneMap.put("zoneName",zoneName);

		List<Zone> zoneList = zoneDao.getZones(zoneMap);

		if(zoneList!=null){
			return zoneList.get(0);
		}else
			return null;
	}
	/**
	 * 根据市code获取区列表
	 * @param map
	 * @return
	 */
	public List<Zone> getAreaInfoByPcode(Map<String, Object> map) {
		return this.zoneDao.getAreaInfoByPcode(map);
	}

	public Zone getNameByPid(Map<String, Object> map) {
		return zoneDao.getNameByPid(map);
	}

	public Zone getIdByName(Map<String, Object> map) {
		return zoneDao.getIdByName(map);
	}

	public List<Zone> getZones(Map<String, Object> map) {
		return zoneDao.getZones(map);
	}

	public Zone getIdByNames(Map<String, Object> map) {
		return zoneDao.getIdByNames(map);
	}

	public Zone getZoneByCode(String zoneCode) {
		return this.zoneDao.getZoneByCode(zoneCode);
	}
}
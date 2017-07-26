package com.xgh.sportsite.dao.read;

import com.xgh.sportsite.entity.SysUnits;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * SysUnitsDao read
 *
 * @author h2y
 *
 * @time:2016-01-12 18:24:34
 *
 * @Email:
 */
@Component
public interface ISysUnitsDaoR {
	
	/**
	 * get
	 * @return
	 */
	public SysUnits get(long id);
	
	/**
	 * getByNid
	 * @return
	 */
	public SysUnits getByNid(long nid);
	
	/**
	 * getList
	 * @return
	 */
	public List<SysUnits> getList(Map<String, Object> map);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<SysUnits> getListPage(Map<String, Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public long getRows(Map<String, Object> map);

	/**
	 * 通过zone信息获取地方代理
	 * @param map
	 * @return
	 */
	public SysUnits getAgencyUnitsByZone(Map<String, Object> map);
}
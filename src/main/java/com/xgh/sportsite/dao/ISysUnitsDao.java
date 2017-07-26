package com.xgh.sportsite.dao;


import com.xgh.sportsite.entity.SysUnits;

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
public interface ISysUnitsDao {

	/**
	 * add
	 */
	public int add(SysUnits sysUnits);
	
	/**
	 * update
	 */
	public int update(SysUnits sysUnits);
	
	/**
	 * delete
	 */
	public int deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public SysUnits get(long id);
	
	
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
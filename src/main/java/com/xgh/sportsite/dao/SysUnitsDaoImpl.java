package com.xgh.sportsite.dao;


import com.xgh.sportsite.dao.read.ISysUnitsDaoR;
import com.xgh.sportsite.dao.write.ISysUnitsDaoW;
import com.xgh.sportsite.entity.SysUnits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * SysUnitsDao Impl
 *
 * @author h2y
 *
 * @time:2016-01-12 18:24:34
 *
 * @Email:
 */
@Service("sysUnitsDao")
public class SysUnitsDaoImpl implements ISysUnitsDao{
 
	@Autowired
	protected ISysUnitsDaoR sysUnitsDaoR;

	@Autowired
	protected ISysUnitsDaoW sysUnitsDaoW;
	
	/**
	 * add
	 */
	public int add(SysUnits sysUnits){
		return sysUnitsDaoW.add(sysUnits);
	}
	
	/**
	 * update
	 */
	public int update(SysUnits sysUnits){
		return sysUnitsDaoW.update(sysUnits);
	}
	
	/**
	 * delete
	 */
	public int deleteById(long id){
		return sysUnitsDaoW.deleteById(id);
	}

	/**
	 * get
	 * @return
	 */
	public SysUnits get(long id){
		return sysUnitsDaoR.get(id);
	}
	
	
	/**
	 * getList
	 * @return
	 */
	public List<SysUnits> getList(Map<String,Object> map){
		return sysUnitsDaoR.getList(map);
	}
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<SysUnits> getListPage(Map<String,Object> map){
		return sysUnitsDaoR.getListPage(map);
	}
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public long getRows(Map<String,Object> map){
		return sysUnitsDaoR.getRows(map);
	}

	/**
	 * 通过zone信息获取地方代理
	 * @param map
	 * @return
	 */
	public SysUnits getAgencyUnitsByZone(Map<String, Object> map) {
		return sysUnitsDaoR.getAgencyUnitsByZone(map);
	}
}
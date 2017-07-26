package com.xgh.sportsite.services;


import com.xgh.sportsite.entity.SysUnits;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * SysUnitsService
 *
 * @author h2y
 *
 * @time:2016-01-12 18:24:34
 *
 * @Email:
 */
public interface ISysUnitsService {
	
	public int add(SysUnits sysUnits);

	public int update(SysUnits sysUnits);
	
	public int save(SysUnits sysUnits);

	public int delete(long id);
	
	public SysUnits get(long id);
	
	public List<SysUnits> getList(Map<String, Object> map);
	
	public List<SysUnits> getOrgList(HttpServletRequest request);

	/**
	 * 通过zone信息获取地方代理
	 * @param zoneCode
	 * @return
	 */
	public SysUnits getAgencyUnitsByZone(String zoneCode);

}

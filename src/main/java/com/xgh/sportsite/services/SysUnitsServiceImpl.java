package com.xgh.sportsite.services;

import com.xgh.sportsite.basic.BaseService;
import com.xgh.sportsite.dao.ISysUnitsDao;
import com.xgh.sportsite.entity.SysUnits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * SysUnitsService Impl
 *
 * @author h2y
 * @time:2016-01-12 18:24:34
 * @Email:
 */
@Service("sysUnitsService")
public class SysUnitsServiceImpl extends BaseService implements ISysUnitsService {


    @Autowired
    protected ISysUnitsDao sysUnitsDao;

    /**
     * Add data, pay attention to the path of the gain of algorithm
     *
     * @param sysUnits
     */
    public int add(SysUnits sysUnits) {
        // TODO Auto-generated method stub

        return sysUnitsDao.add(sysUnits);
    }

    public int update(SysUnits sysUnits) {
        // TODO Auto-generated method stub
        return sysUnitsDao.update(sysUnits);
    }

    public int save(SysUnits sysUnits) {
        //此处认为shop不为空，进行操作，是否为空的判断在controller中进行
        if (sysUnits != null && sysUnits.getId() < 1) {
            //该处认为是添加操作
            Date date = new Date();

            sysUnits.setCreateDate(date);
            sysUnits.setUpdateDate(date);
            sysUnits.setStatus(1);

            return add(sysUnits);
        } else {
            SysUnits sysUnits2 = get(sysUnits.getId());

            sysUnits2.setUpdateDate(new Date());
            return update(sysUnits);
        }
    }

    public int delete(long id) {
        // TODO Auto-generated method stub
        return sysUnitsDao.deleteById(id);
    }

    public SysUnits get(long id) {
        // TODO Auto-generated method stub
        return sysUnitsDao.get(id);
    }

    public List<SysUnits> getList(Map<String, Object> map) {

        return sysUnitsDao.getList(map);
    }

    /**
     * 获取组织列表数据
     *
     * @param request
     * @return
     */
    public List<SysUnits> getOrgList(HttpServletRequest request) {



		String page = request.getParameter("page");
		String pageSize = request.getParameter("pageSize");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("unitKind",2);
		map.put("instId",getCurrentInstId(request));
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pageSize));

		List<SysUnits> sysUnitsList = new ArrayList<SysUnits>();

		List<SysUnits> dataList = sysUnitsDao.getListPage(map);
		if (dataList != null){
			for(SysUnits sysUnits:dataList){
				//sysUnits.setLogoUrl(getServerUrl()+sysUnits.getLogoPath());
				sysUnitsList.add(sysUnits);
			}
		}

		return sysUnitsList;
    }

    /**
     * 通过zone信息获取地方代理
     *
     * @param zoneCode
     * @return
     */
    public SysUnits getAgencyUnitsByZone(String zoneCode) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("zoneCode", zoneCode);
        params.put("instCode", "edu");
        params.put("unitType", 3);

        return sysUnitsDao.getAgencyUnitsByZone(params);
    }

}
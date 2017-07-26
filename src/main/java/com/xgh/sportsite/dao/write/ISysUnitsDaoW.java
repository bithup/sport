package com.xgh.sportsite.dao.write;

import com.xgh.sportsite.entity.SysUnits;
import org.springframework.stereotype.Component;

/**
 * SysUnitsDao read
 *
 * @author h2y
 * @time:2016-01-12 18:24:34
 * @Email:
 */
@Component
public interface ISysUnitsDaoW {

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
     * deleteByNid
     */
    public int deleteByNid(long nid);
}
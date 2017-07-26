package com.xgh.sportsite.dao.write;

import com.xgh.sportsite.entity.Zone;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ZoneDao read
 *
 * @author duanxg
 * @time:2016-02-18 11:53:33
 * @Email:
 */
@Component
public interface IZoneDaoW {

    /**
     * add
     */
    public int add(Zone zone);

    /**
     * addBatch
     */
    public int addBatch(List<Zone> list);

    /**
     * update
     */
    public int update(Zone zone);

    /**
     * delete
     */
    public int deleteById(long id);
}
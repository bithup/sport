package com.xgh.sportsite.dao;

import com.xgh.sportsite.dao.read.IVenueDaoR;
import com.xgh.sportsite.dao.write.IVenueDaoW;
import com.xgh.sportsite.entity.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by CQ on 2016/12/8.
 */
@Service("venueDao")
public class VenueDaoImpl implements IVenueDao {

    @Autowired
    protected IVenueDaoR venueDaoR;

    @Autowired
    protected IVenueDaoW venueDaoW;

    public Venue get(long id) {
        return venueDaoR.get(id);
    }

    public int add(Venue venue) {
        return venueDaoW.add(venue);
    }
    public List<Venue> getVenueByPhone(String telPhone) {
        return venueDaoR.getVenueByPhone(telPhone);
    }
    public int update(Venue venue) {
        return venueDaoW.update(venue);
    }

}

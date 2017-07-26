package com.xgh.sportsite.dao.read;

import com.xgh.sportsite.entity.Venue;

import java.util.List;

/**
 * Created by CQ on 2016/12/8.
 */
public interface IVenueDaoR {

    /**
     * 查询
     * @param id
     * @return
     */
    public Venue get(long id);

    /***
     * 根据电话查场馆
     * @param telPhone
     * @return
     */
    public List<Venue> getVenueByPhone(String telPhone);


}

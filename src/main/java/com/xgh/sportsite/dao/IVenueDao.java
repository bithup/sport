package com.xgh.sportsite.dao;

import com.xgh.sportsite.entity.Venue;

import java.util.List;

/**
 * Created by CQ on 2016/12/8.
 */
public interface IVenueDao {


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

    /**
     * 商户注册时新增场馆
     * @param venue
     * @return
     */
    int add(Venue venue);
    /**
     * 修改场馆信息
     * @param venue
     * @return
     */
    int update(Venue venue);
}

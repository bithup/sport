package com.xgh.sportsite.services;

import com.xgh.sportsite.entity.Venue;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by CQ on 2016/12/8.
 */
public interface IVenueService  {


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
    public int updateVenue(HttpServletRequest request, Venue venue);

}

package com.xgh.sportsite.services;

import com.xgh.sportsite.entity.ShortMessage;

/**
 * Created by Administrator on 2016/12/21.
 */
public interface IShortMessageService {


    public ShortMessage get(long id);


    public int delete(long id);


    public int insert(ShortMessage shortMessage);


    public int update(ShortMessage shortMessage);
}

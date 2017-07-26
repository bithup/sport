package com.xgh.sportsite.dao.write;

import com.xgh.sportsite.entity.ShortMessage;

/**
 * Created by Administrator on 2016/12/21.
 */
public interface IShortMessageDaoW {


    public int delete(long id);


    public int insert(ShortMessage shortMessage);


    public int update(ShortMessage shortMessage);
}

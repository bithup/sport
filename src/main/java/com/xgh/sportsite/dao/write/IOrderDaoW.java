package com.xgh.sportsite.dao.write;

import com.xgh.sportsite.entity.Order;

/**
 * Created by Administrator on 2016/12/12.
 */
public interface IOrderDaoW {

    public int delete(long id);

    public int insert(Order order);

    public int update(Order order);
}

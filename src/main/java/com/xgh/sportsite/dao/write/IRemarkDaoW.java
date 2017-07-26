package com.xgh.sportsite.dao.write;

import com.xgh.sportsite.entity.Remark;

/**
 * Created by Administrator on 2016/12/19.
 */
public interface IRemarkDaoW {

    public int delete(long id);

    public int insert(Remark remark);

    public int update(Remark remark);
}

package com.xgh.sportsite.dao;

import com.xgh.sportsite.entity.Remark;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/19.
 */
public interface IRemarkDao {

    public Remark get(long id);

    public int delete(long id);

    public int insert(Remark remark);

    public int update(Remark remark);

    public List<Remark> getByDataId(Map<String,Object> map);
}

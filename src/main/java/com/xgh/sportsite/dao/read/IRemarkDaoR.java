package com.xgh.sportsite.dao.read;

import com.xgh.sportsite.entity.Remark;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/19.
 */
public interface IRemarkDaoR {

    public Remark get(long id);

    public List<Remark> getByDataId(Map<String,Object> map);
}

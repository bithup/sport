package com.xgh.sportsite.dao;

import com.xgh.sportsite.dao.read.IRemarkDaoR;
import com.xgh.sportsite.dao.write.IRemarkDaoW;
import com.xgh.sportsite.entity.Remark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/19.
 */
@Service("remarkDao")
public class RemarkDaoImpl implements IRemarkDao{

    @Autowired
    protected IRemarkDaoR remarkDaoR;

    @Autowired
    protected IRemarkDaoW remarkDaoW;


    public Remark get(long id) {
        return remarkDaoR.get(id);
    }

    public int delete(long id) {
        return remarkDaoW.delete(id);
    }

    public int insert(Remark remark) {
        return remarkDaoW.insert(remark);
    }

    public int update(Remark remark) {
        return remarkDaoW.update(remark);
    }

    public List<Remark> getByDataId(Map<String, Object> map) {
        return remarkDaoR.getByDataId(map);
    }
}

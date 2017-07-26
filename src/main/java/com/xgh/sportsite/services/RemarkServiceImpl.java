package com.xgh.sportsite.services;

import com.xgh.sportsite.dao.IRemarkDao;
import com.xgh.sportsite.entity.Remark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/19.
 */
@Service("remarkService")
public class RemarkServiceImpl implements IRemarkService{

    @Autowired
    protected IRemarkDao remarkDao;

    public Remark get(long id) {
        return remarkDao.get(id);
    }

    public int delete(long id) {
        return remarkDao.delete(id);
    }

    public int insert(Remark remark) {
        return remarkDao.insert(remark);
    }

    public int update(Remark remark) {
        return remarkDao.update(remark);
    }

    public List<Remark> getByDataId(Map<String, Object> map) {
        return remarkDao.getByDataId(map);
    }
}

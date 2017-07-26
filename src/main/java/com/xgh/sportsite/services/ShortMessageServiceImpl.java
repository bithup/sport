package com.xgh.sportsite.services;

import com.xgh.sportsite.dao.IShortMessageDao;
import com.xgh.sportsite.entity.ShortMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/12/21.
 */
@Service("shortMessageService")
public class ShortMessageServiceImpl implements IShortMessageService {

    @Autowired
    protected IShortMessageDao shortMessageDao;

    public ShortMessage get(long id) {
        return shortMessageDao.get(id);
    }

    public int delete(long id) {
        return shortMessageDao.delete(id);
    }

    public int insert(ShortMessage shortMessage) {
        return shortMessageDao.insert(shortMessage);
    }

    public int update(ShortMessage shortMessage) {
        return shortMessageDao.update(shortMessage);
    }
}

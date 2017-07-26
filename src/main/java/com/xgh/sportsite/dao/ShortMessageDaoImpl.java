package com.xgh.sportsite.dao;

import com.xgh.sportsite.dao.read.IShortMessageDaoR;
import com.xgh.sportsite.dao.write.IShortMessageDaoW;
import com.xgh.sportsite.entity.ShortMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/12/21.
 */
@Service("shortMessageDao")
public class ShortMessageDaoImpl implements IShortMessageDao {

    @Autowired
    protected IShortMessageDaoR shortMessageDaoR;

    @Autowired
    protected IShortMessageDaoW shortMessageDaoW;


    public ShortMessage get(long id) {
        return shortMessageDaoR.get(id);
    }

    public int delete(long id) {return shortMessageDaoW.delete(id);}

    public int insert(ShortMessage shortMessage) {
        return shortMessageDaoW.insert(shortMessage);
    }

    public int update(ShortMessage shortMessage) {
        return shortMessageDaoW.update(shortMessage);
    }
}

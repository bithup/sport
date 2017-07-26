package com.xgh.sportsite.dao;

import com.xgh.sportsite.dao.read.IKindsDaoR;
import com.xgh.sportsite.dao.write.IKindsDaoW;
import com.xgh.sportsite.entity.Kinds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */
@Service("kindsDao")
public class KindsDaoImpl implements IKindsDao {

    @Autowired
    protected IKindsDaoR kindsDaoR;

    @Autowired
    protected IKindsDaoW kindsDaoW;

    public Kinds get(long id) {
        return kindsDaoR.get(id);
    }

    /**
     * 首页运动类型
     * @return
     */
    public List<Kinds> getIndexKinds() {
        return kindsDaoR.getIndexKinds();
    }

    public int delete(long id) {
        return kindsDaoW.delete(id);
    }

    public int insert(Kinds kinds) {
        return kindsDaoW.insert(kinds);
    }

    public int update(Kinds kinds) {
        return kindsDaoW.update(kinds);
    }

    public List<Kinds> getIndexOtherKinds() {
        return kindsDaoR.getIndexOtherKinds();
    }

    public List<Kinds> getHeadKinds() {
        return kindsDaoR.getHeadKinds();
    }
}

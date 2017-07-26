package com.xgh.sportsite.dao.write;

import com.xgh.sportsite.entity.MemberUser;

/**
 * Created by Administrator on 2016/12/6.
 */
public interface IMemberUserDaoW {

    public int delete(long id);

    public int insert(MemberUser memberUser);

    public int update(MemberUser memberUser);

}

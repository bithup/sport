package com.xgh.sportsite.services;

import com.xgh.sportsite.entity.MemberUser;
import com.xgh.util.MatcherUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/6.
 */
public interface IMemberUserService {

    public MemberUser get(long id);



    public int insert(MemberUser memberUser);

    public int update(MemberUser memberUser);

    public List<MemberUser> getRepeatAccount(String account);

    public Map<String,Object> login(HttpServletRequest request);

}

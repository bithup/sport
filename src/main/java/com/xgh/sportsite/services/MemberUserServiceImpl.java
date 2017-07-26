package com.xgh.sportsite.services;

import com.xgh.security.MD5Util;
import com.xgh.sportsite.basic.BaseService;
import com.xgh.sportsite.dao.ICoachDao;
import com.xgh.sportsite.dao.IMemberUserDao;
import com.xgh.sportsite.dao.ITotalMemberUserDao;
import com.xgh.sportsite.dao.IVenueDao;
import com.xgh.sportsite.entity.Coach;
import com.xgh.sportsite.entity.MemberUser;
import com.xgh.sportsite.entity.TotalMemebrUser;
import com.xgh.sportsite.entity.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Administrator on 2016/12/6.
 */
@Service("memberUserService")
public class MemberUserServiceImpl extends BaseService implements IMemberUserService {

    @Autowired
    protected IMemberUserDao memberUserDao;

    @Autowired
    protected IVenueDao venueDao;

    @Autowired
    protected ICoachDao coachDao;

    @Autowired
    protected ITotalMemberUserDao totalMemberUserDao;


    public MemberUser get(long id) {
        return memberUserDao.get(id);
    }

    public int insert(MemberUser memberUser) {

        int flg = 0;

        TotalMemebrUser totalMemebrUser_ = totalMemberUserDao.login(memberUser.getAccount());

        TotalMemebrUser totalMemebrUser = new TotalMemebrUser();
        if (totalMemebrUser_ == null) {

            totalMemebrUser.setAccount(memberUser.getAccount());
            totalMemebrUser.setPassword(memberUser.getPassword());
            totalMemebrUser.setType(3);//从运动平台注册的
            totalMemebrUser.setCreateDate(new Date());
            totalMemebrUser.setUpdateDate(new Date());
            totalMemebrUser.setStatus(1);
            totalMemberUserDao.add(totalMemebrUser);//先注册到总表里面

        }


        Date date = new Date();

        int type = memberUser.getData7();//注册类型 1，用户；2、教练；3、场馆',

        if (type == 1) {

            int flag = memberUserDao.insert(memberUser);//插入用户表
            if (flag > 0) {

                MemberUser coachMemberUser = new MemberUser();

                coachMemberUser.setAccount(memberUser.getAccount());
                coachMemberUser.setPassword(memberUser.getPassword());
                coachMemberUser.setIsVerify(0);
                coachMemberUser.setUuid(UUID.randomUUID().toString());
                coachMemberUser.setUserResource(1);
                coachMemberUser.setStatus(1);
                coachMemberUser.setCreateDate(new Date());
                coachMemberUser.setUpdateDate(new Date());
                coachMemberUser.setStatus(1);
                coachMemberUser.setData7(2);

                Coach coach = new Coach();
                coach.setTelPhone(memberUser.getAccount());
                coach.setCreateDate(date);
                coach.setUpdateDate(date);
                coach.setStatus(1);
                coach.setIsRecommend(0);
                coach.setIsCheck(0);
                coach.setData4(2L);
                coach.setData5(1L);
                coachDao.add(coach);
                coachMemberUser.setData4(coach.getId());

                memberUserDao.insert(coachMemberUser);

                //插入场馆
                MemberUser venueMemberUser = new MemberUser();
                venueMemberUser.setAccount(memberUser.getAccount());
                venueMemberUser.setPassword(memberUser.getPassword());
                venueMemberUser.setIsVerify(0);
                venueMemberUser.setUuid(UUID.randomUUID().toString());
                venueMemberUser.setUserResource(1);
                venueMemberUser.setStatus(1);
                venueMemberUser.setCreateDate(new Date());
                venueMemberUser.setUpdateDate(new Date());
                venueMemberUser.setStatus(1);
                venueMemberUser.setData7(3);

                Venue venue = new Venue();
                venue.setMobile(memberUser.getAccount());
                venue.setCreateDate(date);
                venue.setUpdateDate(date);
                venue.setStatus(1);

                venue.setIsCheck(0);
                venue.setIsRecommend(0);
                venue.setInstId(1L);
                venue.setUnitId(2L);
                venueDao.add(venue);
                venueMemberUser.setData4(venue.getId());

                flg = memberUserDao.insert(venueMemberUser);
                if (flg > 0) {
                    flg = 1;
                } else {
                    flg = 0;
                }
            }
        }

        if (type == 2) {//注册类型 1，用户；2、教练；3、场馆',


            Coach coach = new Coach();
            coach.setTelPhone(memberUser.getAccount());
            coach.setCreateDate(date);
            coach.setUpdateDate(date);
            coach.setStatus(1);
            coach.setIsRecommend(0);
            coach.setIsCheck(0);
            coach.setData4(2L);
            coach.setData5(1L);
            coachDao.add(coach);
            memberUser.setData4(coach.getId());

            memberUserDao.insert(memberUser);//插入教练表

            MemberUser memberUser_ = new MemberUser();
            memberUser_.setAccount(memberUser.getAccount());
            memberUser_.setPassword(memberUser.getPassword());
            memberUser_.setIsVerify(0);
            memberUser_.setUuid(UUID.randomUUID().toString());
            memberUser_.setUserResource(1);
            memberUser_.setCreateDate(new Date());
            memberUser_.setUpdateDate(new Date());
            memberUser_.setStatus(1);
            memberUser_.setData7(1);//插入用户表

            memberUserDao.insert(memberUser_);

            //插入场馆
            MemberUser venueMemberUser = new MemberUser();
            venueMemberUser.setAccount(memberUser.getAccount());
            venueMemberUser.setPassword(memberUser.getPassword());
            venueMemberUser.setIsVerify(0);
            venueMemberUser.setUuid(UUID.randomUUID().toString());
            venueMemberUser.setUserResource(1);
            venueMemberUser.setStatus(1);
            venueMemberUser.setCreateDate(new Date());
            venueMemberUser.setUpdateDate(new Date());
            venueMemberUser.setData7(3);

            Venue venue = new Venue();
            venue.setMobile(memberUser.getAccount());
            venue.setCreateDate(date);
            venue.setUpdateDate(date);
            venue.setStatus(1);

            venue.setIsCheck(0);
            venue.setIsRecommend(0);
            venue.setInstId(1L);
            venue.setUnitId(2L);
            venueDao.add(venue);
            venueMemberUser.setData4(venue.getId());

            flg = memberUserDao.insert(venueMemberUser);
            if (flg > 0) {
                flg = 1;
            } else {
                flg = 0;
            }
        }


        if (type == 3) {//注册类型 1，用户；2、教练；3、场馆',

            Venue venue = new Venue();
            venue.setMobile(memberUser.getAccount());
            venue.setCreateDate(date);
            venue.setUpdateDate(date);
            venue.setStatus(1);

            venue.setIsCheck(0);
            venue.setIsRecommend(0);
            venue.setInstId(1L);
            venue.setUnitId(2L);
            venueDao.add(venue);
            memberUser.setData4(venue.getId());
            memberUserDao.insert(memberUser);//插入教练信息

            MemberUser memberUser_ = new MemberUser();
            memberUser_.setAccount(memberUser.getAccount());
            memberUser_.setPassword(memberUser.getPassword());
            memberUser_.setIsVerify(0);
            memberUser_.setUuid(UUID.randomUUID().toString());
            memberUser_.setUserResource(1);
            memberUser_.setCreateDate(new Date());
            memberUser_.setUpdateDate(new Date());
            memberUser_.setStatus(1);
            memberUser_.setData7(1);//插入用户信息

            memberUserDao.insert(memberUser_);

            MemberUser coachMemberUser = new MemberUser();

            coachMemberUser.setAccount(memberUser.getAccount());
            coachMemberUser.setPassword(memberUser.getPassword());
            coachMemberUser.setIsVerify(0);
            coachMemberUser.setUuid(UUID.randomUUID().toString());
            coachMemberUser.setUserResource(1);
            coachMemberUser.setStatus(1);
            coachMemberUser.setCreateDate(new Date());
            coachMemberUser.setUpdateDate(new Date());
            coachMemberUser.setData7(2);

            Coach coach = new Coach();
            coach.setTelPhone(memberUser.getAccount());
            coach.setCreateDate(date);
            coach.setUpdateDate(date);
            coach.setStatus(1);
            coach.setIsRecommend(0);
            coach.setIsCheck(0);
            coach.setData4(2L);
            coach.setData5(1L);
            coachDao.add(coach);
            coachMemberUser.setData4(coach.getId());

            flg = memberUserDao.insert(coachMemberUser);//插入教练信息
            if (flg > 0) {
                flg = 1;
            } else {
                flg = 0;
            }
        }
        return flg;
    }

    public int update(MemberUser memberUser) {
        return memberUserDao.update(memberUser);
    }

    public List<MemberUser> getRepeatAccount(String account) {
        return memberUserDao.getRepeatAccount(account);
    }

    public Map<String, Object> login(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String account = request.getParameter("account");
        String password = MD5Util.getMD5(request.getParameter("password"));
        String type = request.getParameter("type");
        if (account == null || "".equals(account)) {
            return getResultMap("0", "用户名不能为空！");
        } else if (password == null || "".equals(password)) {
            return getResultMap("0", "密码不能为空！");
        }

        TotalMemebrUser totalMemberUser = totalMemberUserDao.login(account);
        if (totalMemberUser == null) {
            resultMap = getResultMap("0", "请先注册！");
            return resultMap;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("account", account);
        map.put("type", type);

        TotalMemebrUser totalMemebrUser = totalMemberUserDao.login(account);
        if (totalMemebrUser != null) {

            String password_ = totalMemebrUser.getPassword();
            if (password_.equals(password)) {
                //用户登录
                if ("1".equals(type)) {

                    MemberUser memberUser = memberUserDao.login(map);//用户
                    if (memberUser != null) {
                        resultMap = getResultMap("1", "登录成功!", memberUser);
                    } else {
                        MemberUser member = new MemberUser();

                        member.setUuid(UUID.randomUUID().toString());
                        member.setAccount(account);
                        member.setPassword(password);
                        member.setCreateDate(new Date());
                        member.setUpdateDate(new Date());
                        member.setStatus(1);
                        member.setData7(1);//用户
                        int flag = memberUserDao.insert(member);
                        if (flag > 0) {
                            resultMap = getResultMap("1", "登录成功!", member);
                        }
                    }
                }

                //教练登录
                if ("2".equals(type)) {

                    MemberUser memberUser = memberUserDao.login(map);//用户
                    if (memberUser != null) {
                        resultMap = getResultMap("1", "登录成功!", memberUser);
                    } else {

                        Coach coach = new Coach();
                        coach.setTelPhone(account);
                        coach.setCreateDate(new Date());
                        coach.setUpdateDate(new Date());
                        coach.setIsRecommend(0);
                        coach.setIsCheck(0);
                        coach.setData4(2L);
                        coach.setData5(1L);
                        coach.setStatus(1);

                        coachDao.add(coach);

                        MemberUser member = new MemberUser();

                        member.setUuid(UUID.randomUUID().toString());
                        member.setAccount(account);
                        member.setPassword(password);
                        member.setCreateDate(new Date());
                        member.setUpdateDate(new Date());
                        member.setStatus(1);
                        member.setData7(2);//教练
                        member.setData4(coach.getId());

                        int flag = memberUserDao.insert(member);
                        if (flag > 0) {
                            resultMap = getResultMap("1", "登录成功!", member);
                        }

                    }
                }
                //场馆登录
                if ("3".equals(type)) {

                    MemberUser memberUser = memberUserDao.login(map);//用户
                    if (memberUser != null) {
                        resultMap = getResultMap("1", "登录成功!", memberUser);
                    } else {

                        Venue venue = new Venue();
                        venue.setMobile(account);
                        venue.setCreateDate(new Date());
                        venue.setUpdateDate(new Date());
                        venue.setStatus(1);
                        venue.setIsCheck(0);
                        venue.setIsRecommend(0);
                        venue.setInstId(1L);
                        venue.setUnitId(2L);

                        venueDao.add(venue);

                        MemberUser member = new MemberUser();

                        member.setUuid(UUID.randomUUID().toString());
                        member.setAccount(account);
                        member.setPassword(password);
                        member.setCreateDate(new Date());
                        member.setUpdateDate(new Date());
                        member.setStatus(1);
                        member.setData7(3);//教练
                        member.setData4(venue.getId());

                        int flag = memberUserDao.insert(member);
                        if (flag > 0) {
                            resultMap = getResultMap("1", "登录成功!", member);
                        }
                    }
                }
            } else {
                resultMap = getResultMap("0", "密码错误!");
            }
        }
        return resultMap;
    }
}

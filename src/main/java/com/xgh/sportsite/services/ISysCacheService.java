package com.xgh.sportsite.services;

import com.xgh.sportsite.entity.MemberUser;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 系统缓存接口
 */
public interface ISysCacheService {

    /**
     * 更新用户缓存信息（登陆成功调用）
     *
     * @param request
     * @param memberUser
     * @update：2015年11月9日 下午11:33:01
     */
    void updateMemberUserCach(HttpServletRequest request, MemberUser memberUser);


    /**
     * 清除用户缓存信息
     *
     * @param request
     */
    void clearMemberUserCach(HttpServletRequest request);


    /**
     * 用户是否登陆，如果已经登录，则更新有效时间
     *
     * @param request
     * @return
     */
    boolean isUserLogin(HttpServletRequest request);


    /**
     * 得到登陆用户Id
     *
     * @return
     */
    long getCurrentMemberUserId(HttpServletRequest request);


    /**
     * 得到登录用户对象
     *
     * @return
     */
    MemberUser getCurrentMemberUser(HttpServletRequest request);

    int updateObject(HttpServletRequest request, String key, Object object);

    Object getObject(HttpServletRequest request, String key);

    Map<String, Object> getMemberUserMap(HttpServletRequest request);
}

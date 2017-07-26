package com.xgh.sportsite.services;

import com.xgh.sportsite.entity.MemberUser;
import com.xgh.sportsite.util.ConstantUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统缓存接口实现类
 *
 * @author:段晓刚
 * @update：2015年11月3日 下午11:04:32
 * @Email：
 */
@Service("sysCacheService")
public class SysCacheServiceImpl implements ISysCacheService {

    private final static Logger logger = Logger.getLogger(SysCacheServiceImpl.class);

    private static int cacheSeconds = 7 * 24 * 60 * 60 * 1000;

    protected HttpSession getSession(HttpServletRequest request) {
        HttpSession session = request.getSession();

        return session;
    }

    protected String getSessionId(HttpServletRequest request) {
        HttpSession session = getSession(request);

        if (session != null)
            return session.getId();
        else
            return "0";
    }

    /**
     * 更新用户缓存信息
     *
     * @param request
     * @param memberUser
     */
    public void updateMemberUserCach(HttpServletRequest request, MemberUser memberUser) {

        long memberId = memberUser.getId();

        Map<String, Object> userMap = new HashMap<String, Object>();

        userMap.put(ConstantUtil.SessionKeys.Member.value(), memberUser);

        //更新用户对应信息
        getSession(request).setAttribute(getCurrentMemberUserKey(request), userMap);
    }

    /**
     * 清除缓存
     */
    public void clearMemberUserCach(HttpServletRequest request) {

        //本地缓存

        //删除对应的用户信息
        getSession(request).removeAttribute(getCurrentMemberUserKey(request));
    }

    /**
     * 用户是否登陆，如果已经登录，则更新有效时间
     *
     * @param request
     * @return
     */
    public boolean isUserLogin(HttpServletRequest request) {

        //本地缓存
        Map<String, Object> userMap = getMemberUserMap(request);

        if (userMap != null && !userMap.isEmpty() && userMap.get(ConstantUtil.SessionKeys.Member.value()) instanceof MemberUser) {

            MemberUser memberUser = (MemberUser) userMap.get(ConstantUtil.SessionKeys.Member.value());

            //更新信息
            getSession(request).setAttribute(getCurrentMemberUserKey(request), userMap);
            return true;
        } else
            return false;
    }

    /**
     * 获取用户信息
     *
     * @param request
     * @return
     */
    public Map<String, Object> getMemberUserMap(HttpServletRequest request) {
        //本地缓存
        Object obj = getSession(request).getAttribute(getCurrentMemberUserKey(request));
        Map<String, Object> userMap = null;
        if (obj != null && obj instanceof Map)
            userMap = (Map<String, Object>) obj;

        return userMap;
    }

    /**
     * 得到用户Id
     */
    public long getCurrentMemberUserId(HttpServletRequest request) {

        MemberUser memberUser = getCurrentMemberUser(request);
        return memberUser != null ? memberUser.getId() : 0;
    }


    /**
     * 更具sessonId获取当前登录用户
     *
     * @param request
     * @return
     */
    public MemberUser getCurrentMemberUser(HttpServletRequest request) {

        MemberUser memberUser = null;
        Map<String, Object> userMap = getMemberUserMap(request);

        if (userMap != null && !userMap.isEmpty()) {
            Object obj = userMap.get(ConstantUtil.SessionKeys.Member.value());

            if (obj != null && obj instanceof MemberUser) {
                memberUser = (MemberUser) obj;
            }
        }
        return memberUser;
    }

    public int updateObject(HttpServletRequest request, String key, Object object) {
        try {
            getSession(request).setAttribute(key, object);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return 0;
        }
    }

    public Object getObject(HttpServletRequest request, String key) {
        return getSession(request).getAttribute(key);
    }

    private String getCurrentMemberUserKey(HttpServletRequest request) {
        return getSessionId(request) + "_member";
    }

    private String getCurrentSessionIdKey(String userId) {
        return "memberId_" + userId;
    }
}

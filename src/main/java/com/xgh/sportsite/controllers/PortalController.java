package com.xgh.sportsite.controllers;

import com.xgh.security.MD5Util;
import com.xgh.sportsite.basic.BaseController;
import com.xgh.sportsite.entity.MemberUser;
import com.xgh.sportsite.entity.TotalMemebrUser;
import com.xgh.sportsite.services.IMemberUserService;
import com.xgh.sportsite.services.ISysCacheService;
import com.xgh.sportsite.services.ITotalMemberUserService;
import com.xgh.sportsite.util.ConstantUtil;
import com.xgh.util.DateUtil;
import com.xgh.util.FileUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Created by Administrator on 2017/3/25.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/portal/")
public class PortalController extends BaseController {

    @Autowired
    protected IMemberUserService memberUserService;

    @Autowired
    protected ISysCacheService sysCacheService;

    @Autowired
    protected ITotalMemberUserService totalMemberUserService;

    /*
   * 用户注册
   * */
    @RequestMapping("registerInit")
    public ModelAndView registerInit(){
        ModelAndView view=new ModelAndView();
        view.setViewName("member/registerInit");
        return view;
    }
    @RequestMapping(value="register")
    public void register(){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String validationCode = request.getParameter("validationCode");
        String type = request.getParameter("type");
        String sessionCode = (String) request.getSession(false).getAttribute("validationCode");
        //request.getSession().removeAttribute("validationCode");
        if (account == null || "".equals(account)) {
            resultMap = getResultMap("0", "用户名不能为空!");
        } else {

            TotalMemebrUser totalMemebrUser = totalMemberUserService.login(account);
            if (totalMemebrUser != null) {
                resultMap = getResultMap("0", "此号码已经被注册,请直接登录!");
                outJson(resultMap);
                return;
            }

            if (password == null || "".equals(password)) {
                resultMap = getResultMap("0", "密码不能为空!");
            } else {
                if (validationCode == null || "".equals(validationCode)) {
                    resultMap = getResultMap("0", "请输入验证码!");
                } else if (sessionCode == null || "".equals(sessionCode)) {
                    resultMap = getResultMap("0", "验证码已过期!");
                } else if (!validationCode.equals(sessionCode)) {
                    resultMap = getResultMap("0", "验证码错误!");
                } else {
                    MemberUser memberUser = new MemberUser();
                    memberUser.setAccount(account);
                    memberUser.setPassword(MD5Util.getMD5(password));
                    if (null != type && !"".equals(type)) {
                        memberUser.setData7(Integer.parseInt(type));//用户类型
                    } else {
                        memberUser.setData7(1);
                    }
                    memberUser.setUuid(UUID.randomUUID().toString());
                    Date date = new Date();
                    memberUser.setCreateDate(date);
                    memberUser.setUpdateDate(date);
                    //目前未对手机号的有效性进行验证，获取需要进行验证
                    memberUser.setIsVerify(0);
                    memberUser.setUserResource(1);
                    memberUser.setStatus(1);
                    int flag = memberUserService.insert(memberUser);
                    if (flag > 0) {
                        resultMap = getResultMap("1", "注册成功！", memberUser);
                        sysCacheService.updateMemberUserCach(request, memberUser);
                    } else {
                        resultMap = getResultMap("0", "出错了，请重试！");
                    }
                }
            }
        }
        outJson(resultMap);
    }
    //登录验证
    @RequestMapping(value="accountIsRepeat")
    public void accountIsRepeat(){
        String account = request.getParameter("account");
        Map<String,Object> resultMap = new HashMap<String, Object>();
        int isRepeat = memberUserService.getRepeatAccount(account).size();
        if (isRepeat>0) {
            resultMap = getResultMap("1","此号码已经被注册！");
        }else {
            resultMap = getResultMap("0","此号码未注册！");
        }
        outJson(resultMap);
    }

    /*
    * 用户登录
    * */
    @RequestMapping(value="loginInit")
    public ModelAndView initLogin(){
        ModelAndView view=new ModelAndView();
        view.setViewName("member/loginInit");
        return view;
    }
    @RequestMapping(value="login")
    public void login(){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        resultMap=memberUserService.login(request);
        MemberUser memberUser=(MemberUser) resultMap.get("resultData");
        if(memberUser!=null){
            updateCurrentMemberUser(request,memberUser);
            resultMap.remove("resultData");
        }
        outJson(resultMap);
    }

    /*
    * 忘记密码，重置密码
    * */
    @RequestMapping("forgetPasswordInit")
    public ModelAndView forgetPasswordInit(){
        ModelAndView view=new ModelAndView();
        view.setViewName("member/forgetPasswordInit");
        return view;
    }
    @RequestMapping(value="forgetPassword")
    public void forgetPassword(){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String validationCode = request.getParameter("validationCode");
        String sessionCode = (String) request.getSession().getAttribute("validationCode");
        //request.getSession().removeAttribute("validationCode");
        List<MemberUser> list =  memberUserService.getRepeatAccount(account);
        int isRepeat = list.size();
        if (account == null || "".equals(account)) {
            resultMap = getResultMap("0", "用户名不能为空!");
        }else if (password == null || "".equals(password)) {
            resultMap = getResultMap("0", "密码不能为空!");
        }else if(validationCode ==null || "".equals(validationCode)){
            resultMap = getResultMap("0", "请输入验证码!");
        }else if(sessionCode == null || "".equals(sessionCode)){
            resultMap = getResultMap("0", "验证码已过期!");
        }else if(!validationCode.equals(sessionCode)){
            resultMap = getResultMap("0", "验证码错误!");
        }else if(isRepeat>1){
            resultMap = getResultMap("0", "账号错误!");
        }else if(isRepeat==0){
            resultMap = getResultMap("0", "用户名不存在!");
        }else if(isRepeat==1){
            MemberUser memberUser = list.get(0);
            memberUser.setPassword(MD5Util.getMD5(password));
            memberUser.setUpdateDate(new Date());
            int flag = memberUserService.update(memberUser);
            if(flag>0){
                resultMap = getResultMap("1","操作成功！");
            }else{
                resultMap = getResultMap("0","操作失败，请重试！");
            }
        }
        outJson(resultMap);

    }

}

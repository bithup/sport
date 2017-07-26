package com.xgh.sportsite.controllers;

import com.xgh.security.MD5Util;
import com.xgh.sportsite.basic.BaseController;
import com.xgh.sportsite.entity.MemberUser;
import com.xgh.sportsite.entity.Order;
import com.xgh.sportsite.entity.Refund;
import com.xgh.sportsite.services.IMemberUserService;
import com.xgh.sportsite.services.IOrderService;
import com.xgh.sportsite.services.IRefundService;
import com.xgh.sportsite.util.ConstantUtil;
import com.xgh.util.DateUtil;
import com.xgh.util.FileUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2016/12/6.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/member/")
public class MemberUserController extends BaseController {

    private Logger logger = Logger.getLogger(MemberUserController.class);

    @Autowired
    protected IMemberUserService memberUserService;

    @Autowired
    protected IOrderService orderService;

    @Autowired
    protected IRefundService refundService;


    /*
    * 用户详细信息获取和更新
    * */
    @RequestMapping("showMemberInfo")
    public ModelAndView showMemberInfo() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/login-wo");
        String coachId = request.getParameter("coachId");
        String status_ = request.getParameter("status_");
        //获取session中的用户信息userMap.put(ConstantUtil.SessionKeys.Member.value(), memberUser);
        MemberUser memberUser = (MemberUser) sysCacheService.getMemberUserMap(request).get(ConstantUtil.SessionKeys.Member.value());
        MemberUser memberUser1 = memberUserService.get(memberUser.getId());
        int flag = 1;
        if (memberUser1.getData7() == 1) {
            flag = 1;
        }
        if (memberUser1.getData7() == 2) {
            flag = 2;
        }
        if (memberUser1.getData7() == 3) {
            flag = 3;
        }
        view.addObject("memberUser", memberUser1);
        view.addObject("coachId", coachId);
        view.addObject("coachId", coachId);
        view.addObject("flag", flag);
        view.addObject("status_", status_);
        return view;
    }

    /*
    * 我的设置页面
    *
    * */
    @RequestMapping("mySet")
    public ModelAndView mySet() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/mySet");
        MemberUser memberUser = (MemberUser) sysCacheService.getMemberUserMap(request).get(ConstantUtil.SessionKeys.Member.value());
        //修改信息之后要重新去数据库查询，并将session更新
        MemberUser memberUser1 = memberUserService.get(memberUser.getId());
        sysCacheService.updateMemberUserCach(request,memberUser1);
        //memberUser.setBirthday(memberUser1.getBirthday());
        view.addObject("memberUser", memberUser1);
        return view;
    }

    @RequestMapping(value = "updateMemberInfo")
    public void updateMemberInfo(@ModelAttribute MemberUser memberUser) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String nickName = memberUser.getNickName();
        int sex = memberUser.getSex();
        try {
            /*if(null==nickName||"".equals(nickName)){
                resultMap = getResultMap("0","昵称不能为空！");
            }else if(sex==0){
                resultMap = getResultMap("0","性别不能为空！");
            }else{*/
            MemberUser memberUser1 = memberUserService.get(memberUser.getId());
            if (nickName != null && !"".equals(nickName)) {
                memberUser1.setNickName(nickName);
            }
            if (sex != 0) {
                memberUser1.setSex(sex);
            }
            if (memberUser.getBirthday() != null && !"".equals(memberUser.getBirthday())) {
                memberUser1.setBirthday(memberUser.getBirthday());
            }
            if (memberUser.getHeigth() != null && !"".equals(memberUser.getHeigth())) {
                memberUser1.setHeigth(memberUser.getHeigth());
            }
            if (memberUser.getWeigth() != null && !"".equals(memberUser.getWeigth())) {
                memberUser1.setWeigth(memberUser.getWeigth());
            }
            if (memberUser.getZoneId() != null && memberUser.getZoneId() > 0) {
                memberUser1.setZoneId(memberUser.getZoneId());
            }
            if (memberUser.getHobby() != null && !"".equals(memberUser.getHobby())) {
                memberUser1.setHobby(memberUser.getHobby());
            }
            if (memberUser.getSignature() != null && !"".equals(memberUser.getSignature())) {
                memberUser1.setSignature(memberUser.getSignature());
            }
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator fileNames = multipartRequest.getFileNames();//可以上传一张也可以上传多张图片

            for (int i = 0; fileNames.hasNext(); ++i) {
                String name = (String) fileNames.next();
                MultipartFile myfile = multipartRequest.getFile(name);
                if (myfile.isEmpty()) {
                    logger.info("文件未上传");
                } else {
                    String OriginalFileName = myfile.getOriginalFilename();
                    String saveName = DateUtil.getSystemTime().getTime() + "" + i + OriginalFileName.substring(OriginalFileName.lastIndexOf("."), OriginalFileName.length());
                    SimpleDateFormat formatdate = new SimpleDateFormat("yyyy/MM/dd/");
                    String relative_path = formatdate.format(new Date());
                    String serverPath = ConstantUtil.SERVER_URL;
                    String realPath = ConstantUtil.SAVE_PATH + "/" + "member" + "/" + relative_path;
                    memberUser1.setHeadPath("/" + "member" + "/" + relative_path + saveName);
                    memberUser1.setHeadRealPath(serverPath + "/" + "member" + "/" + relative_path + saveName);
                    File filePath = new File(realPath);
                    if (!filePath.exists()) {
                        filePath.mkdirs();
                    }
                    FileUtil.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, saveName));
                }
            }
            Date date = new Date();
            memberUser1.setUpdateDate(date);
            int flag = memberUserService.update(memberUser1);
            if (flag > 0) {
                resultMap = getResultMap("1", "操作成功！", memberUser1);
            } else {
                resultMap = getResultMap("0", "出错了，请重试！");
            }
           /* }*/
        } catch (IOException var18) {
            var18.printStackTrace();
            logger.error(var18.getMessage(), var18);
        }
        outJson(resultMap);
    }

    /*
    * 跳转到修改密码页面
    * */
    @RequestMapping("updatePasswordInit")
    public ModelAndView updatePasswordInit() {
        ModelAndView view = new ModelAndView();
        String id = request.getParameter("id");
        view.addObject("id", id);
        view.setViewName("member/modifyPassword");
        return view;
    }


    /*
    * 修改密码
    * */
    @RequestMapping(value = "updatePassword")
    public void updatePassword() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String oldPass = request.getParameter("oldPass");
        MemberUser memberUser = memberUserService.get(Long.parseLong(id));
        if (oldPass == null || "".equals(oldPass)) {
            resultMap = getResultMap("0", "原密码不能为空!");
        } else if (memberUser == null) {
            resultMap = getResultMap("0", "系统错误！");
        } else if (!MD5Util.getMD5(oldPass).equals(memberUser.getPassword())) {
            resultMap = getResultMap("0", "原密码错误!");
        } else {
            if (password == null || "".equals(password)) {
                resultMap = getResultMap("0", "密码不能为空!");
            } else {
                memberUser.setPassword(MD5Util.getMD5(password));
                memberUser.setUpdateDate(new Date());
                int flag = memberUserService.update(memberUser);
                if (flag > 0) {
                    resultMap = getResultMap("1", "修改成功！");
                } else {
                    resultMap = getResultMap("0", "修改失败！");
                }
            }
        }
        outJson(resultMap);
    }


    /**
     * 钱包余额
     */
    @RequestMapping(value = "getPurseBalance")
    public void getPurseBalance() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String memberId = request.getParameter("memberId");
        MemberUser memberUser = memberUserService.get(Long.parseLong(memberId));
        Double purseBalance = memberUser.getPurseBalance();
        if (purseBalance == null) {
            purseBalance = 0d;
        }
        if (memberUser != null) {
            resultMap = getResultMap("1", "获取成功！", purseBalance);
        } else {
            resultMap = getResultMap("0", "获取失败！");
        }
        outJson(resultMap);
    }


    /**
     * 设置提现密码
     */
    @RequestMapping(value = "setPayPassword")
    public void setPayPassword() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String memberId = request.getParameter("memberId");
        String payPassword = request.getParameter("payPassword");
        String checkPassword = request.getParameter("checkPassword");
        String password = request.getParameter("password");
        MemberUser memberUser = memberUserService.get(Long.parseLong(memberId));
        String md5Password = MD5Util.getMD5(password);
        if (payPassword.equals(checkPassword)) {
            if (md5Password.equals(memberUser.getPassword())) {
                memberUser.setPayPassword(MD5Util.getMD5(payPassword));
                int flag = memberUserService.update(memberUser);
                if (flag > 0) {
                    resultMap = getResultMap("1", "设置成功！");
                } else {
                    resultMap = getResultMap("0", "设置失败，请重试！");
                }
            } else {
                resultMap = getResultMap("0", "登录密码错误！");
            }
        } else {
            resultMap = getResultMap("0", "提现密码和确认密码不一致！");
        }
        outJson(resultMap);
    }

    /*
    * 我的活动页面跳转
    * */
    @RequestMapping("myActivity")
    public ModelAndView myActivity() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/myActivity");
        return view;
    }

    /*
    * 我的收藏页面跳转
    * */
    @RequestMapping("myCollect")
    public ModelAndView myCollect() {
        ModelAndView view = new ModelAndView();
        MemberUser memberUser = (MemberUser) sysCacheService.getMemberUserMap(request).get(ConstantUtil.SessionKeys.Member.value());
        view.setViewName("member/myCollect");
        String status_ = request.getParameter("status_");
        view.addObject("status_", status_);
        view.addObject("memberId", memberUser.getId());
        return view;
    }

    /*
    * 我的钱包页面跳转
    * */
    @RequestMapping("myWallet")
    public ModelAndView myWallet() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/myWallet");
        MemberUser memberUser = (MemberUser) sysCacheService.getMemberUserMap(request).get(ConstantUtil.SessionKeys.Member.value());
        Double purseBalance = memberUser.getPurseBalance();
        DecimalFormat df = new DecimalFormat("######0.00");
        if (purseBalance == null) {
            purseBalance = 0.00;

        }
        view.addObject("purseBalance", df.format(purseBalance));
        return view;
    }

    /*
    * 我的订单页面跳转 买家版
    * */
    @RequestMapping("myOrder")
    public ModelAndView myOrder() {
        ModelAndView view = new ModelAndView();
        String id = request.getParameter("id");
        String role = request.getParameter("role");
        view.addObject("id", id);
        view.addObject("role", role);

        view.setViewName("member/myOrder");
        return view;
    }

    /*
    * 我的订单页面跳转 卖家版
    * */
    @RequestMapping(value = "order")
    public ModelAndView order() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/order");
        String id = request.getParameter("id");
        String role = request.getParameter("role");
        view.addObject("id", id);
        view.addObject("role", role);
        return view;
    }

    @RequestMapping(value = "refund")
    public ModelAndView refund() {
        ModelAndView view = new ModelAndView();
        view.setViewName("member/refund");
        String orderId=request.getParameter("orderId");
        view.addObject("orderId",orderId);
        String memberId = sysCacheService.getCurrentMemberUserId(request) + "";
        view.addObject("memberId", memberId);
        return view;
    }

    @RequestMapping(value = "logout")
    public ModelAndView logout() {
        sysCacheService.clearMemberUserCach(request);
        ModelAndView view = new ModelAndView();
        view.setViewName("member/loginInit");
        return view;

    }


}

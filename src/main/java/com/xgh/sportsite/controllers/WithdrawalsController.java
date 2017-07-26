package com.xgh.sportsite.controllers;

import com.xgh.security.MD5Util;
import com.xgh.sportsite.basic.BaseController;
import com.xgh.sportsite.entity.MemberUser;
import com.xgh.sportsite.entity.Withdrawals;
import com.xgh.sportsite.services.IMemberUserService;
import com.xgh.sportsite.services.IWithdrawalsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/21.
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/withdrawals")
public class WithdrawalsController extends BaseController{

    private Logger logger = Logger.getLogger(MemberUserController.class);

    @Autowired
    protected IWithdrawalsService withdrawalsService;

    @Autowired
    protected IMemberUserService memberUserService;


    /**
     * 申请提现
     * @param withdrawals
     * @return
     */
    @RequestMapping(value = "/getWithdrawals")
    public void getWithdrawals(@ModelAttribute Withdrawals withdrawals){
        HashMap<String,Object> resultMap = new HashMap<String, Object>();
        Long id = Long.parseLong(request.getParameter("memberId"));
        MemberUser memberUser = memberUserService.get(id);
        String payPassword = MD5Util.getMD5( request.getParameter("payPassword"));
        if(null==memberUser.getPayPassword()||"".equals(memberUser.getPayPassword())){
            resultMap.put("code",0);
            resultMap.put("msg","请先设置提现密码！");
        }else{
            if(memberUser.getPayPassword().equals(payPassword)){
                if(memberUser.getPurseBalance()!=null&&memberUser.getPurseBalance()>1){
                    double cashWithdrawalAmount = memberUser.getPurseBalance();
                    double realAmount = cashWithdrawalAmount*0.998;
                    double fee = cashWithdrawalAmount*0.002;
                    DecimalFormat df = new DecimalFormat("#.00");
                    double realAmount1 = Double.parseDouble(df.format(realAmount));
                    double fee1 = Double.parseDouble(df.format(fee));
                    Date date = new Date();
                    withdrawals.setCashWithdrawalAmount(cashWithdrawalAmount);
                    withdrawals.setRealAmount(realAmount1);
                    withdrawals.setFee(fee1);
                    withdrawals.setInstId(getCurrentInstId());
                    withdrawals.setMemberId(id);
                    withdrawals.setMemberType(memberUser.getData7());
                    withdrawals.setCreateDate(date);
                    withdrawals.setUpdateDate(date);
                    withdrawals.setStatus(0);
                    withdrawals.setDrawStatus(0);
                    int flag = withdrawalsService.insert(withdrawals);
                    memberUser.setPurseBalance(0.00);
                    int flag1 = memberUserService.update(memberUser);
                    if(flag>0&&flag1>0){
                        resultMap.put("code",1);
                        resultMap.put("msg","申请成功，请耐心等待审核！");
                    }else{
                        resultMap.put("code",0);
                        resultMap.put("msg","申请出错，请重试！");
                    }
                }else{
                    resultMap.put("code",0);
                    resultMap.put("msg","余额过小，不能提现！");
                }
            }else{
                resultMap.put("code",0);
                resultMap.put("msg","提现密码错误！");
            }
        }
        outJson(resultMap);
    }


    /**
     * 获取提现记录
     */
    @RequestMapping(value="getWithdrawalsList")
    public void getWithdrawalsList(){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        Map<String,Object> map = new HashMap<String, Object>();
        String memberId = request.getParameter("memberId");
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        map.put("memberId",memberId);
        map.put("page",Integer.parseInt(page));
        map.put("pagesize",Integer.parseInt(pagesize));
        List<Map<String,Object>> list = withdrawalsService.getListPage(map);
        if(list!=null&&list.size()>0){
            resultMap = getResultMap("1","获取提现记录成功！",list);
        }else {
            resultMap = getResultMap("0","暂无数据！");
        }
        outJson(resultMap);
    }

    /**
     * 删除提现记录、撤销提现申请
     */
    @RequestMapping(value="cancelWithdrawals")
    public void cancelWithdrawals(){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        String withdrawalsId = request.getParameter("withdrawalsId");
        Withdrawals withdrawals = withdrawalsService.get(Long.parseLong(withdrawalsId));
        withdrawals.setStatus(-1);
        int flag = withdrawalsService.update(withdrawals);
        if(flag>0){
            resultMap = getResultMap("1","操作成功！");
        }else{
            resultMap = getResultMap("0","操作失败！");
        }
        outJson(resultMap);

    }
}

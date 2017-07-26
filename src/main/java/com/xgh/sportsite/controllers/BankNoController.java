package com.xgh.sportsite.controllers;

import com.xgh.sportsite.basic.BaseController;
import com.xgh.sportsite.dao.IBankNoDao;
import com.xgh.sportsite.entity.BankNo;
import com.xgh.sportsite.services.IBankNoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/11/14.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "sportsite/bankNo/")
public class BankNoController extends BaseController {

    @Autowired
    protected IBankNoService bankNoService;

    @Autowired
    protected IBankNoDao bankNoDao;


    /**
     * 显示绑定银行卡列表
     */
    @RequestMapping(value = "getBankNoByMemId")
    public void getBankNoByMemId() {

        outJson(bankNoService.getBankNoByMemId(request));

    }


    /**
     * 默认绑定银行卡
     */
    @RequestMapping(value = "setDefaultBankNo")
    public void setDefaultBankNo() {

        outJson(bankNoService.getDefaultBankNo(request));

    }


    /**
     * 解除银行卡绑定
     */
    @RequestMapping(value = "unbindBanking")
    public void unbindBanking() {

        outJson(bankNoService.unbindBanking(request));
    }


}

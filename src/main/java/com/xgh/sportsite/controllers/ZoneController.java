package com.xgh.sportsite.controllers;

import com.xgh.sportsite.basic.BaseController;
import com.xgh.sportsite.entity.Zone;
import com.xgh.sportsite.services.IZoneService;
import com.xgh.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/25 0025.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/zone/")
public class ZoneController extends BaseController {
    @Autowired
    protected IZoneService zoneService;
    /**
     * 根据市代码获取市所属区域信息列表
     * @author xiaowenbo
     * @date 2016年3月25日 11:49:23
     */
    @RequestMapping(value = "getAreaListByPcode")
    public void getAreaInfoByPcode() {
        Map<String, Object> postMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        List<Zone> list = null;
        //市代码
        String pcode = request.getParameter("pcode");
        if(StringUtil.notEmpty(pcode)){
            postMap.put("pcode",pcode);
        }
        list = this.zoneService.getAreaInfoByPcode(postMap);
        if(list.size()>0){
            map.put("resultFlag","1");
            map.put("resultMsg","获取信息成功");
        }else{
            map.put("resultFlag","0");
            map.put("resultMsg","服务器异常");
        }
        map.put("resultData",list);
        outJson(map);
    }

    @RequestMapping("switchZone")
    public ModelAndView SwitchZone(){
        ModelAndView view=new ModelAndView();
        view.setViewName("zone/switchZone");
        return view;
    }
}

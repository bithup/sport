package com.xgh.sportsite.controllers;

import com.xgh.sportsite.basic.BaseController;
import com.xgh.sportsite.entity.House;
import com.xgh.sportsite.entity.MemberUser;
import com.xgh.sportsite.services.IHouseService;
import com.xgh.sportsite.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/12/15.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/house/")
public class HouseController extends BaseController {

    @Autowired
    protected IHouseService houseService;


    /**
     * 添加收藏
     */
    @RequestMapping(value = "addToHouse")
    public void addToHouse(@ModelAttribute House house) {
        MemberUser memberUser=(MemberUser) sysCacheService.getMemberUserMap(request).get(ConstantUtil.SessionKeys.Member.value());
        house.setMemberId(memberUser.getId());
        outJson(houseService.save(request, house));
    }

    /**
     * 取消收藏
     */
    @RequestMapping(value = "cancelToHouse")
    public void cancelToHouse(@ModelAttribute House house) {
        outJson(houseService.update(request, house));
    }

    /**
     * 收藏列表查询
     */
    @RequestMapping(value = "getHouseList")
    public void getHouseList(@ModelAttribute House house) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", house.getType());
        map.put("memberId", house.getMemberId());
        outJson(houseService.getHouseList(request, map));
    }

    /**
     * 批量删除收藏活动
     */
    @RequestMapping(value = "deleteHouseActive")
    public void deleteHouseActive() {

        outJson(houseService.batchUpdateByIdList(request));
    }

    @RequestMapping(value="isHouse")
    public void isHouse(@ModelAttribute House house){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        long memberId = house.getMemberId();
        long dataId = house.getDataId();
        int type = house.getType();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("memberId",memberId);
        map.put("dataId",dataId);
        map.put("type",type);
        List<House> houses = houseService.checkHouseExist(map);
        if(houses!=null&&houses.size()==1){
            House house1 = houses.get(0);
            if(house1.getStatus()==0){
                resultMap = getResultMap("2","未收藏");
            }else if(house1.getStatus()==1){
                resultMap = getResultMap("1","收藏");
            }
        }else if(houses!=null&&houses.size()==0){
            resultMap = getResultMap("2","未收藏");
        }else{
            resultMap = getResultMap("0","系统数据错误！");
        }
        outJson(resultMap);
    }
}

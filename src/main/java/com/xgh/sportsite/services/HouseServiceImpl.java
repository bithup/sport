package com.xgh.sportsite.services;

import com.xgh.sportsite.basic.BaseService;
import com.xgh.sportsite.dao.IHouseDao;
import com.xgh.sportsite.entity.House;
import com.xgh.sportsite.entity.MemberUser;
import com.xgh.sportsite.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by CQ on 2016/12/15.
 */
@Service("houseService")
public class HouseServiceImpl extends BaseService implements IHouseService {

    @Autowired
    protected IHouseDao houseDao;


    public House get(long id) {
        return houseDao.get(id);
    }


    public Map<String, Object> getHouseList(HttpServletRequest request, Map<String, Object> map) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");
        map.put("page", Integer.parseInt(page));
        map.put("pageSize", Integer.parseInt(pageSize));
        int type = Integer.parseInt(map.get("type")+"");
        List<Map<String, Object>> houseList = null;
        if(type==0){
            houseList = houseDao.getVenueHouseList(map);
        }else if(type ==1){
            houseList = houseDao.getCoachHouseList(map);
        }else if(type == 2){
            houseList = houseDao.getActivityHouseList(map);
        }
        if (houseList != null && houseList.size() > 0) {
            resultMap = getResultMap("1", "收藏列表成功!", houseList);
        } else {
            resultMap = getResultMap("0", "收藏列表失败!");
        }

        return resultMap;
    }

    public Map<String, Object> save(HttpServletRequest request, House house) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        long memberId = house.getMemberId();
        long dataId = house.getDataId();
        int type = house.getType();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("memberId",memberId);
        map.put("dataId",dataId);
        map.put("type",type);
        List<House> list = houseDao.checkHouseExist(map);
        if(list!=null && list.size()==0){
            house.setStatus(1);
            house.setCreateDate(new Date());
            house.setUpdateDate(new Date());
            int flag = houseDao.add(house);
            if (flag > 0) {
                resultMap = getResultMap("1", "收藏成功");
            } else {
                resultMap = getResultMap("0", "收藏失败");
            }
        }else if(list!=null && list.size()==1){
            House house1 = list.get(0);
            house1.setUpdateDate(new Date());
            if(list.get(0).getStatus()==1){
                house1.setStatus(0);
                int flag = houseDao.update(house1);
                if (flag > 0) {
                    resultMap = getResultMap("2", "取消收藏成功");
                } else {
                    resultMap = getResultMap("0", "取消收藏失败");
                }
            }else if(list.get(0).getStatus()==0||list.get(0).getStatus()==-1){
                house1.setStatus(1);
                int flag = houseDao.update(house1);
                if (flag > 0) {
                    resultMap = getResultMap("1", "收藏成功");
                } else {
                    resultMap = getResultMap("0", "收藏失败");
                }
            }

        }else{
            resultMap = getResultMap("0", "收藏失败");
        }
        return resultMap;
    }

    public Map<String, Object> update(HttpServletRequest request, House house) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        String houseId = request.getParameter("houseId");
        house = houseDao.get(Long.parseLong(houseId));
        house.setUpdateDate(new Date());
        house.setStatus(0);
        int flag = houseDao.update(house);
        if (flag > 0) {
            resultMap = getResultMap("1", "取消收藏成功");
        } else {
            resultMap = getResultMap("0", "取消收藏失败");
        }
        return resultMap;
    }

    public List<House> checkHouseExist(Map<String, Object> map) {
        return houseDao.checkHouseExist(map);
    }


    public int batchUpdateList(List<Map<String, Object>> list) {
        return houseDao.batchUpdateList(list);
    }

    /**
     * 批量删除数据
     *
     * @param request
     * @return
     */

    public Map<String, Object> batchUpdateByIdList(HttpServletRequest request) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String houseId_ = request.getParameter("houseId");
        String[] houseId = houseId_.split(",");
        List<String> list = new ArrayList<String>(Arrays.asList(houseId));

 /*     houseDao.batchUpdateWithArray(houseId);//传递数组进行批量更新*/

        //map结构类型的方法
/*      Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", -2);
        map.put("idList", list);
        houseDao.batchUpdateMap(map);*/

        //数组转list的第二种方式
/*      List<String> list = new ArrayList<String>();
        Collections.addAll(list, houseId);*/

        try {
            long beginTime = System.currentTimeMillis();
            int flag = houseDao.batchUpdateByIdList(list);
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - beginTime;
            System.out.println("消耗时间............." + totalTime);
            if (flag > 0) {
                resultMap = getResultMap("1", "删除收藏列表成功!");
            }
        } catch (Exception e) {
            resultMap = getResultMap("0", "删除收藏列表失败!");
        }
        return resultMap;
    }

    public Map<String, Object> batchUpdateMap(HttpServletRequest request) {
        return null;
    }
}

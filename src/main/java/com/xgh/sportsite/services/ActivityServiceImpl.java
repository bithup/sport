package com.xgh.sportsite.services;

import com.xgh.sportsite.basic.BaseService;
import com.xgh.sportsite.dao.IActivityDao;
import com.xgh.sportsite.dao.IOrderDao;
import com.xgh.sportsite.entity.Activity;
import com.xgh.sportsite.util.ConstantUtil;
import com.xgh.util.DateUtil;
import com.xgh.util.FileUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by CQ on 2016/12/12.
 */
@Service("ActivityService")
public class ActivityServiceImpl extends BaseService implements IActivityService {

    private Logger logger = Logger.getLogger(ActivityServiceImpl.class);


    @Autowired
    protected IActivityDao activityDao;

    @Autowired
    protected IOrderDao orderDao;

    public Map<String, Object> getActivityCount(HttpServletRequest request) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        Map<String, Object> map = new HashMap<String, Object>();
        String memberId = request.getParameter("memberId");
        String goodsId = request.getParameter("goodsId");
        map.put("memberId", memberId);
        map.put("goodsId", goodsId);
        try {
            long count = orderDao.getActivityCount(map);
            resultMap = getResultMap("1", "预定数量显示成功!", count);
        } catch (Exception e) {
            resultMap = getResultMap("0", "预定数量显示失败!");
        }
        return resultMap;
    }

    public int save(HttpServletRequest request, Activity activity) {

        if (activity != null && activity.getId() < 1) {

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

                    String realPath = ConstantUtil.SAVE_PATH + "/" + ConstantUtil.FileUploadCode.Active.value() + "/" + relative_path;
                    activity.setActivityPath("/" + ConstantUtil.FileUploadCode.Active.value() + "/" + relative_path + saveName);
                    activity.setActivityRealPath(serverPath + "/" + ConstantUtil.FileUploadCode.Active.value() + "/" + relative_path + saveName);

                    File filePath = new File(realPath);
                    if (!filePath.exists()) {
                        filePath.mkdirs();
                    }
                    try {
                        FileUtil.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, saveName));
                    } catch (IOException var18) {
                        var18.printStackTrace();
                        logger.error(var18.getMessage(), var18);
                    }
                }
            }
            activity.setIsCheck(1);//审核通过
            activity.setStatus(1);
            activity.setIsFree(1);//1免费
            activity.setCreateDate(new Date());
            activity.setUpdateDate(new Date());
            return activityDao.add(activity);
        } else {

            Activity activity1 = get(activity.getId());
            activity1.setUpdateDate(new Date());
            return activityDao.update(activity1);
        }

    }

    public Activity get(long id) {
        return activityDao.get(id);
    }


    public Map<String, Object> getActivitySignDetail(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String memberId = request.getParameter("memberId");
        String goodsId = request.getParameter("goodsId");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("memberId", memberId);
        map.put("goodsId", goodsId);
        List<Map<String, Object>> mapList = orderDao.getActivitySignDetail(map);
        if (mapList != null && mapList.size() > 0) {
            resultMap = getResultMap("1", "报名详情列表显示成功!", mapList);
        } else {
            resultMap = getResultMap("0", "报名详情列表显示失败!");
        }

        return resultMap;
    }

    public List<Map<String, Object>> getActivityBySportId(Map<String, Object> map) {
        return activityDao.getActivityBySportId(map);
    }

    public List<Map<String, Object>> getActivityIndexResearch(Map<String, Object> map) {
        return activityDao.getActivityIndexResearch(map);
    }

    public List<Map<String, Object>> getRecommendActivity(Map<String, Object> map) {
        return activityDao.getRecommendActivity(map);
    }

    public List<Map<String, Object>> getListPage(Map<String, Object> map) {
        return activityDao.getListPage(map);
    }


    public Map<String, Object> getPublishActivity(HttpServletRequest request, Map<String, Object> map) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String memberId = request.getParameter("memberId");
        map.put("memberId", memberId);
        List<Map<String, Object>> mapList = activityDao.getPublishActivity(map);

        if (mapList != null && mapList.size() > 0) {

            for (Map<String, Object> map_ : mapList) {
                String goodsId = map_.get("activeId") + "";//活动id
                map.put("goodsId", goodsId);
                long count = orderDao.getActivityCount(map);
                map_.put("activeCount", count);
            }
            resultMap = getResultMap("1", "发布活动列表成功!", mapList);
        } else {
            resultMap = getResultMap("0", "发布活动列表失败!");
        }

        return resultMap;
    }

    public Map<String, Object> cancel(HttpServletRequest request) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String activeId = request.getParameter("goodsId");
        if (!"".equals(activeId)) {
            Activity activity = activityDao.get(Long.parseLong(activeId));
            activity.setStatus(-1);
            activity.setUpdateDate(new Date());
            int flag = activityDao.update(activity);
            if (flag > 0) {
                resultMap = getResultMap("1", "活动取消成功!");
            } else {
                resultMap = getResultMap("0", "活动取消失败!");
            }
        }
        return resultMap;
    }
}

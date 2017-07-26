package com.xgh.sportsite.services;

import com.xgh.sportsite.dao.IChildVenueDao;
import com.xgh.sportsite.dao.IVenueDao;
import com.xgh.sportsite.entity.ChildVenue;
import com.xgh.sportsite.entity.FileData;
import com.xgh.sportsite.entity.Venue;
import com.xgh.sportsite.entity.Zone;
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
 * Created by Administrator on 2016/12/8.
 */
@Service("childVanueService")
public class ChildVenueServiceImpl implements IChildVanueService{

    private Logger logger = Logger.getLogger(ChildVenueServiceImpl.class);

    @Autowired
    protected IVenueDao venueDao;

    @Autowired
    protected IChildVenueDao childVenueDao;

    @Autowired
    protected IFileDataService fileDataService;

    /**
     * 根据id查询场馆
     * @param id
     * @return
     */
    public ChildVenue get(long id) {
        return childVenueDao.get(id);
    }

    /**
     * 热门场馆
     * @param request
     * @return
     */
    public List<Map<String, Object>> getRecommendVenue(HttpServletRequest request) {
        Map<String,Object> map = new HashMap<String, Object>();
        String zoneName = request.getParameter("zoneName");
        map.put("zoneName",zoneName);
        return childVenueDao.getRecommendVenue(map);
    }

    /**
     * 分页查询场馆列表
     * @param map
     * @return
     */
    public List<Map<String, Object>> getListPage(Map<String, Object> map) {

        List<Map<String, Object>> list = childVenueDao.getListPage(map);
        return list;
    }

    /**
     * 场馆详情
     * @param id
     * @return
     */
    public Map<String, Object> getDetail(long id) {
        return childVenueDao.getDetail(id);
    }

    /**
     * 条件查询场馆列表
     * @param map
     * @return
     */
    public List<Map<String, Object>> getVenueByPid(Map<String, Object> map) {
        return childVenueDao.getVenueByPid(map);
    }

    /**
     * 首页搜索
     * @param map
     * @return
     */
    public List<Map<String,Object>> getIndexSearch(Map<String,Object> map){
        return childVenueDao.getIndexSearch(map);
    }

    public int update(ChildVenue childVenue) {
        return childVenueDao.update(childVenue);
    }

    /**
     * 发布分场馆
     * @param request
     * @param childVenue
     * @return
     */
    public int addChildVenue(HttpServletRequest request, ChildVenue childVenue){
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Date date = new Date();
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator fileNames = multipartRequest.getFileNames();
            if (fileNames!=null&&fileNames.hasNext()) {
                for (int i = 0; fileNames.hasNext(); ++i) {
                    String name = (String) fileNames.next();
                    MultipartFile myfile = multipartRequest.getFile(name);
                    if (myfile.isEmpty()) {
                        logger.info("文件未上传");
                    } else {
                        String OriginalFileName = myfile.getOriginalFilename();
                        String saveName = DateUtil.getSystemTime().getTime() + ""+ i + OriginalFileName.substring(OriginalFileName.lastIndexOf("."), OriginalFileName.length());
                        SimpleDateFormat formatdate = new SimpleDateFormat("yyyy/MM/dd/");
                        String relative_path = formatdate.format(date);
                        String serverPath = ConstantUtil.SERVER_URL;
                        String realPath = ConstantUtil.SAVE_PATH + "/" + "childVenue" + "/" + relative_path;
                        File filePath = new File(realPath);
                        if (!filePath.exists()) {
                            filePath.mkdirs();
                        }
                        FileUtil.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, saveName));

                        Map<String,Object> childVenuePicMap = new HashMap<String, Object>();
                        childVenuePicMap.put("relativePath","/" + "childVenue" + "/" + relative_path);
                        childVenuePicMap.put("fileName",saveName);
                        childVenuePicMap.put("oldName",OriginalFileName);
                        childVenuePicMap.put("fileSize",myfile.getSize());
                        childVenuePicMap.put("FileSuffix",saveName.substring(saveName.length() - 3, saveName.length()));
                        if (name.equals("childVenueLogo")){
                            childVenue.setPictureUrl("/" + "childVenue" + "/" + relative_path + saveName);
                            childVenue.setPicRealPath(serverPath + "/" + "childVenue" + "/" + relative_path + saveName);
                            childVenuePicMap.put("dataType",0);
                            childVenuePicMap.put("type",1);
                        }else {
                            childVenuePicMap.put("dataType",1);
                            childVenuePicMap.put("type",0);
                        }
                        mapList.add(childVenuePicMap);
                    }
                }
            }
        } catch (IOException var18) {
            var18.printStackTrace();
            logger.error(var18.getMessage(), var18);
        }
        String venueId = request.getParameter("venueId");
        childVenue.setParentId(Long.parseLong(venueId));
        childVenue.setIsRecommend(0);
        childVenue.setStatus(0);
        childVenue.setCreateDate(date);
        int flag = childVenueDao.add(childVenue);
        if (flag>0){
            for (Map<String,Object> childVenuePicMap_:mapList){
                FileData fileData = new FileData();
                fileData.setNid(0);
                fileData.setInstId(1);
                fileData.setInstNid(1010001);
                fileData.setInstCode("sport");
                fileData.setUnitId(2);
                fileData.setUnitNid(0);
                fileData.setUnitCode("sport");
                fileData.setDataCode(ConstantUtil.FileUploadCode.ChildVenue.value());
                fileData.setDataType(Integer.parseInt(String.valueOf(childVenuePicMap_.get("dataType"))));
                fileData.setDataId(childVenue.getId());
                fileData.setDataVersion(0);
                fileData.setPath(ConstantUtil.SAVE_PATH);
                fileData.setRelativePath(String.valueOf(childVenuePicMap_.get("relativePath")));
                fileData.setFileName(String.valueOf(childVenuePicMap_.get("fileName")));
                fileData.setOldName(String.valueOf(childVenuePicMap_.get("oldName")));
                fileData.setFileSize(Long.parseLong(String.valueOf(childVenuePicMap_.get("fileSize"))));
                fileData.setFileSuffix(String.valueOf(childVenuePicMap_.get("FileSuffix")));
                fileData.setType(Integer.parseInt(String.valueOf(childVenuePicMap_.get("type"))));
                fileData.setStatus(1);
                fileData.setCreateDate(new Date());
                fileDataService.add(fileData);
            }
        }
        return flag;
    }

    /**
     * 编辑分场馆
     * @param request
     * @param childVenue
     * @return
     */
    public int updateChildVenue(HttpServletRequest request, ChildVenue childVenue){
        Date date = new Date();
        String childVenueId = request.getParameter("childVenueId");
        ChildVenue childVenue1 = childVenueDao.get(Long.parseLong(childVenueId));
        if (childVenue1!=null&&childVenue1.getId()>0){
            childVenue1.setVenueName(childVenue.getVenueName());
            childVenue1.setSportId(childVenue.getSportId());
            childVenue1.setVenueNo(childVenue.getVenueNo());
            childVenue1.setPrice(childVenue.getPrice());
            childVenue1.setSalesPrice(childVenue.getSalesPrice());
            childVenue1.setUnit(childVenue.getUnit());
            childVenue1.setServiceInfo(childVenue.getServiceInfo());
            childVenue1.setTips(childVenue.getTips());
            childVenue1.setOrd(childVenue.getOrd());
            childVenue1.setFacility(childVenue.getFacility());
            childVenue1.setCapacity(childVenue.getCapacity());
            childVenue1.setUpdateDate(date);
            try {
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                Iterator fileNames = multipartRequest.getFileNames();
                if (fileNames!=null&&fileNames.hasNext()) {
                    for (int i = 0; fileNames.hasNext(); ++i) {
                        String name = (String) fileNames.next();
                        MultipartFile myfile = multipartRequest.getFile(name);
                        if (myfile.isEmpty()) {
                            logger.info("文件未上传");
                            //没有获取到分场馆主图时，把以前的存入
                            if (name.equals("childVenueLogo")){
                                childVenue1.setPictureUrl(childVenue1.getPictureUrl());
                                childVenue1.setPicRealPath(childVenue1.getPicRealPath());
                            }
                        } else {
                            String OriginalFileName = myfile.getOriginalFilename();
                            String saveName = DateUtil.getSystemTime().getTime() + ""+ i + OriginalFileName.substring(OriginalFileName.lastIndexOf("."), OriginalFileName.length());
                            SimpleDateFormat formatdate = new SimpleDateFormat("yyyy/MM/dd/");
                            String relative_path = formatdate.format(date);
                            String serverPath = ConstantUtil.SERVER_URL;
                            String realPath = ConstantUtil.SAVE_PATH + "/" + "childVenue" + "/" + relative_path;
                            File filePath = new File(realPath);
                            if (!filePath.exists()) {
                                filePath.mkdirs();
                            }
                            FileUtil.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, saveName));

                            FileData fileData = new FileData();
                            fileData.setNid(0);
                            fileData.setInstId(1);
                            fileData.setInstNid(1010001);
                            fileData.setInstCode("sport");
                            fileData.setUnitId(2);
                            fileData.setUnitNid(0);
                            fileData.setUnitCode("sport");
                            fileData.setDataCode(ConstantUtil.FileUploadCode.ChildVenue.value());
                            fileData.setDataId(Long.parseLong(childVenueId));
                            fileData.setDataVersion(0);
                            fileData.setPath(ConstantUtil.SAVE_PATH);
                            fileData.setRelativePath("/" + "childVenue" + "/" + relative_path);
                            fileData.setFileName(saveName);
                            fileData.setOldName(OriginalFileName);
                            fileData.setFileSize(myfile.getSize());
                            fileData.setFileSuffix(saveName.substring(saveName.length() - 3, saveName.length()));
                            fileData.setStatus(1);
                            fileData.setCreateDate(new Date());
                            if (name.equals("childVenueLogo")){
                                childVenue1.setPictureUrl("/" + "childVenue" + "/" + relative_path + saveName);
                                childVenue1.setPicRealPath(serverPath + "/" + "childVenue" + "/" + relative_path + saveName);

                                fileData.setDataType(0);
                                fileData.setType(1);

                                Map<String,Object> map = new HashMap<String, Object>();
                                map.put("dataId", childVenueId);
                                map.put("dataCode", ConstantUtil.FileUploadCode.ChildVenue.value());
                                map.put("dataVersion", 0);
                                map.put("server", ConstantUtil.SERVER_URL);
                                map.put("dataType",0);
                                map.put("type",1);
                                List<Map<String, Object>> fileDataList = fileDataService.getFileDatas(map);//教练上传的身份证正面照
                                if(fileDataList.size()>0){
                                    Map<String,Object> fileData1 =  fileDataList.get(0);
                                    fileData.setId(Long.parseLong(String.valueOf(fileData1.get("id"))));
                                    fileDataService.update(fileData);
                                }else{
                                    fileDataService.add(fileData);
                                }
                            }else {
                                fileData.setDataType(1);
                                fileData.setType(0);
                                fileDataService.add(fileData);
                            }
                        }
                    }
                }
            } catch (IOException var18) {
                var18.printStackTrace();
                logger.error(var18.getMessage(), var18);
            }
            return childVenueDao.update(childVenue1);
        }else {
            return -1;
        }
    }

    /**
     * 删除分场馆
     * @param request
     * @return
     */
    public int deleteChildVenue(HttpServletRequest request){
        String childVenueId = request.getParameter("childVenueId");
        ChildVenue childVenue = childVenueDao.get(Long.parseLong(childVenueId));
        if (childVenue!=null&&childVenue.getId()>0){
            childVenue.setStatus(-1);
            childVenue.setUpdateDate(new Date());
            return childVenueDao.update(childVenue);
        }else {
            return -1;
        }
    }

    public List<Map<String,Object>> getChildVenue(Map<String,Object> map){
        return childVenueDao.getChildVenue(map);
    }

}

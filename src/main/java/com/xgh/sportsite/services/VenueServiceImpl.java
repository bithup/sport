package com.xgh.sportsite.services;

import com.xgh.sportsite.dao.IVenueDao;
import com.xgh.sportsite.dao.IZoneDao;
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
 * Created by CQ on 2016/12/8.
 */
@Service("venueService")
public class VenueServiceImpl implements IVenueService {

    private Logger logger = Logger.getLogger(VenueServiceImpl.class);

    @Autowired
    protected IVenueDao venueDao;

    @Autowired
    protected IZoneDao zoneDao;

    @Autowired
    protected IZoneService zoneService;

    @Autowired
    protected IFileDataService fileDataService;


    public Venue get(long id) {
        return venueDao.get(id);
    }

    public List<Venue> getVenueByPhone(String telPhone) {
        return venueDao.getVenueByPhone(telPhone);
    }

    /**
     * 编辑主场馆
     * @param request
     * @param venue
     * @return
     */
    public int updateVenue(HttpServletRequest request, Venue venue){
        Date date = new Date();
        String venueId = request.getParameter("venueId");

        Venue venue1 = venueDao.get(Long.parseLong(venueId));
        if (venue1!=null&&venue1.getId()>0){
            Zone zone = zoneDao.get(venue.getZoneId());
            String zoneName = getZoneName(zone.getCode());
            venue1.setName(venue.getName());
            venue1.setAddress(venue.getAddress());
            venue1.setZoneId(venue.getZoneId());
            venue1.setZoneName(zoneName);
            venue1.setZoneCode(zone.getCode());
            venue1.setTelephone(venue.getTelephone());
            venue1.setContact(venue.getContact());
            venue1.setSex(venue.getSex());
            venue1.setIntroduction(venue.getIntroduction());
            String startTime = venue.getStartTime();
            String endTime = venue.getEndTime();
            venue1.setStartTime(startTime);
            venue1.setEndTime(endTime);
            if (("0").equals(startTime)&&("0").equals(endTime)){
                venue1.setBusinessTime("24小时");
            }else {
                venue1.setBusinessTime("早"+startTime+"点至晚"+endTime+"点");
            }
            venue1.setLicenseNo(venue.getLicenseNo());
            venue1.setArtificilPerson(venue.getArtificilPerson());
            venue1.setOrganizationType(venue.getOrganizationType());
            venue1.setBusInfo(venue.getBusInfo());
            venue1.setSubwayInfo(venue.getSubwayInfo());
            venue1.setServiceInfo(venue.getServiceInfo());
            venue1.setHardware(venue.getHardware());
            venue1.setIsRefund(venue.getIsRefund());
            if (venue.getIsRefund()==1){
                venue1.setRefundDeadline(venue.getRefundDeadline());
            }
            venue1.setUpdateDate(date);

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
                            String realPath = ConstantUtil.SAVE_PATH + "/" + "venue" + "/" + relative_path;
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
                            fileData.setDataCode(ConstantUtil.FileUploadCode.Venue.value());
                            fileData.setDataId(Long.parseLong(venueId));
                            fileData.setDataVersion(0);
                            fileData.setPath(ConstantUtil.SAVE_PATH);
                            fileData.setRelativePath("/" + "venue" + "/" + relative_path);
                            fileData.setFileName(saveName);
                            fileData.setOldName(OriginalFileName);
                            fileData.setFileSize(myfile.getSize());
                            fileData.setFileSuffix(saveName.substring(saveName.length() - 3, saveName.length()));
                            fileData.setStatus(1);
                            fileData.setCreateDate(new Date());
                            fileData.setOrd(2);
                            if (name.equals("venueLogo")) {
                                venue1.setPictureUrl("/" + "venue" + "/" + relative_path + saveName);
                                venue1.setPicRealPath(serverPath + "/" + "venue" + "/" + relative_path + saveName);

                                fileData.setDataType(0);
                                fileData.setType(1);

                                Map<String, Object> map = new HashMap<String, Object>();
                                map.put("dataId", venueId);
                                map.put("dataCode", ConstantUtil.FileUploadCode.Venue.value());
                                map.put("dataVersion", 0);
                                map.put("server", ConstantUtil.SERVER_URL);
                                map.put("dataType", 0);
                                map.put("type", 1);
                                List<Map<String, Object>> fileDataList = fileDataService.getFileDatas(map);
                                if (fileDataList.size() > 0) {
                                    Map<String, Object> fileData1 = fileDataList.get(0);
                                    fileData.setId(Long.parseLong(String.valueOf(fileData1.get("id"))));
                                    fileDataService.update(fileData);
                                } else {
                                    fileDataService.add(fileData);
                                }
                            }else if(name.equals("licenseFile")){
                                venue1.setLicenseUrl(serverPath + "/" + "venue" + "/" + relative_path + saveName);

                                fileData.setDataType(0);
                                fileData.setType(2);

                                Map<String, Object> map = new HashMap<String, Object>();
                                map.put("dataId", venueId);
                                map.put("dataCode", ConstantUtil.FileUploadCode.Venue.value());
                                map.put("dataVersion", 0);
                                map.put("server", ConstantUtil.SERVER_URL);
                                map.put("dataType", 0);
                                map.put("type", 2);
                                List<Map<String, Object>> fileDataList = fileDataService.getFileDatas(map);
                                if (fileDataList.size() > 0) {
                                    Map<String, Object> fileData1 = fileDataList.get(0);
                                    fileData.setId(Long.parseLong(String.valueOf(fileData1.get("id"))));
                                    fileDataService.update(fileData);
                                } else {
                                    fileDataService.add(fileData);
                                }
                            } else {
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
            return venueDao.update(venue1);
        }else {
            return -1;
        }
    }

    /**
     * 获取省市区
     * @param zoneCode
     * @return
     */
    public String getZoneName( String zoneCode){
        Zone zone =  zoneService.getZoneByCode(zoneCode);
        String zoneName="";
        if(Integer.parseInt(zone.getLevel())==5){
            //市
            Zone zone4 = zoneService.getZoneByCode(zone.getPcode());
            //省
            Zone zone3 = zoneService.getZoneByCode(zone4.getPcode());
            //地区
            Zone zone2 = zoneService.getZoneByCode(zone3.getPcode());
            //国家
            Zone zone1 = zoneService.getZoneByCode(zone2.getPcode());
            zoneName = zone3.getName()+zone4.getName()+zone.getName();
        }else if(Integer.parseInt(zone.getLevel())==4){
            Zone zone3 = zoneService.getZoneByCode(zone.getPcode());
            Zone zone2 = zoneService.getZoneByCode(zone3.getPcode());
            Zone zone1 = zoneService.getZoneByCode(zone2.getPcode());
            zoneName = zone3.getName()+zone.getName();
        }else if(Integer.parseInt(zone.getLevel())==3){
            Zone zone2 = zoneService.getZoneByCode(zone.getPcode());
            Zone zone1 = zoneService.getZoneByCode(zone2.getPcode());
            zoneName = zone2.getName()+zone.getName();
        }else if(Integer.parseInt(zone.getLevel())==2){
            Zone zone1 = zoneService.getZoneByCode(zone.getPcode());
            zoneName = zone1.getName()+zone.getName();
        }else if(Integer.parseInt(zone.getLevel())==1){
            zoneName = zone.getName();
        }
        return zoneName;
    }

}

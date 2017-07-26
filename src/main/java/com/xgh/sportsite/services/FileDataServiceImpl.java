//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xgh.sportsite.services;


import com.xgh.security.Base64Util;
import com.xgh.sportsite.basic.BaseService;
import com.xgh.sportsite.dao.IFileDataDao;
import com.xgh.sportsite.entity.FileData;
import com.xgh.sportsite.util.ConstantUtil;
import com.xgh.util.JSONUtil;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("fileDataService")
public class FileDataServiceImpl extends BaseService implements IFileDataService {
    private Logger logger = Logger.getLogger(FileDataServiceImpl.class);
    @Autowired
    protected IFileDataDao fileDataDao;

    public FileDataServiceImpl() {
    }

    public int add(FileData fileData) {
        return this.fileDataDao.add(fileData);
    }

    public int update(FileData fileData) {
        return this.fileDataDao.update(fileData);
    }
    public int updateBatch(List<String> list) {
        return fileDataDao.updateBatch(list);
    }
    public int delete(long id) {
        return this.fileDataDao.deleteById(id);
    }

    public int updateByDataSource(Map<String, Object> map) {
        return this.fileDataDao.updateByDataSource(map);
    }

    public FileData get(long id) {
        return this.fileDataDao.get(id);
    }

    public List<FileData> getList(Map<String, Object> map) {
        List list = this.fileDataDao.getList(map);
        ArrayList list2 = new ArrayList();
        if(list != null && !list.isEmpty() && map.containsKey("server")) {
            String server = map.get("server") + "";
            Iterator i$ = list.iterator();

            while(i$.hasNext()) {
                FileData fileData = (FileData)i$.next();
                fileData.setUrl(server + "/save_path/" + fileData.getRelativePath() + fileData.getFileName());
                list2.add(fileData);
            }
        }

        return list2;
    }

    public List<FileData> getListPage(Map<String, Object> map) {
        return this.fileDataDao.getListPage(map);
    }

    public long getRows(Map<String, Object> map) {
        return this.fileDataDao.getRows(map);
    }

    public List<Map<String, Object>> getFileDatas(Map<String, Object> map) {
        List list = this.getList(map);
        ArrayList list2 = new ArrayList();
        if(list != null && !list.isEmpty()) {
            Iterator i$ = list.iterator();

            while(i$.hasNext()) {
                FileData fileData = (FileData)i$.next();
                HashMap map2 = new HashMap();
                map2.put("id", Long.valueOf(fileData.getId()));
                map2.put("dataType", Integer.valueOf(fileData.getDataType()));
                map2.put("dataCode", fileData.getDataCode());
                map2.put("dataId", Long.valueOf(fileData.getDataId()));
                map2.put("url", fileData.getUrl().replace("save_path//save_path/","save_path"));
                map2.put("fileName", fileData.getFileName());
                map2.put("oldName", fileData.getOldName());
                map2.put("ord", Integer.valueOf(fileData.getOrd()));
                list2.add(map2);
            }
        }
        return list2;
    }

    public List<FileData> saveFiles(HttpServletRequest request, String[] fileDataArray, long dataId, ConstantUtil.FileUploadCode fileUploadCode, int dataVersion) {
      /*  SysIndustry sysIndustry = this.getCurrentIndustry(request);
        SysUnits sysUnits = this.getCurrentUnits(request);*/
        String saveToPath = ConstantUtil.SAVE_PATH;
        ArrayList fileList = new ArrayList();
        if(fileDataArray != null && fileDataArray.length > 0) {
            ArrayList oldFileDataList = new ArrayList();
            String[] map = fileDataArray;
            int dataVersion2 = fileDataArray.length;

            for(int i$ = 0; i$ < dataVersion2; ++i$) {
                String fd = map[i$];
                Map map1 = JSONUtil.getMap(fd);
                if(map1.get("id") != null) {
                    FileData var26 = this.get(Long.valueOf(map1.get("id") + "").longValue());
                    if(var26 != null) {
                        var26.setDataVersion(dataVersion);
                        fileList.add(var26);
                        oldFileDataList.add(var26);
                    }
                } else {
                    try {
                        String e = fileUploadCode.value();
                        String savePath = Base64Util.decodeToString(map1.get("savePath") + "");
                        String fileName = map1.get("fileName") + "";
                        String oldName = map1.get("oldName") + "";
                        FileData fileData = new FileData();
                 /*       fileData.setInstId(sysIndustry.getId());
                        fileData.setInstNid(sysIndustry.getNid());
                        fileData.setInstCode(sysIndustry.getCode());*/
                  /*      fileData.setUnitId(sysUnits.getId());
                        fileData.setUnitNid(sysUnits.getNid());
                        fileData.setUnitCode(sysUnits.getUnitCode());*/
                        fileData.setStatus(1);
                        fileData.setCreateDate(new Date());
                        fileData.setDataCode(e);
                        fileData.setDataId(dataId);
                        fileData.setDataType(Integer.parseInt(map1.get("fileType") + ""));
                        fileData.setDataVersion(dataVersion);
                        fileData.setFileName(fileName);
                        fileData.setOldName(oldName);
                        fileData.setFileSuffix(fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()));
                        SimpleDateFormat formatdate = new SimpleDateFormat("yyyy/MM/dd/");
                        String relative_path = formatdate.format(new Date());
                        fileData.setRelativePath("/" + e + "/" + relative_path);
                        fileData.setPath(saveToPath);
                        fileData.setFileSize((new File(savePath)).length());
                        if(map1.containsKey("ord") && map1.get("ord") != null) {
                            fileData.setOrd(Integer.parseInt(map1.get("ord") + ""));
                        } else {
                            fileData.setOrd(0);
                        }

                        FileUtils.copyFile(new File(savePath), new File(saveToPath + "/" + e + "/" + relative_path + fileName));
                        fileList.add(fileData);
                    } catch (Exception var24) {
                        var24.printStackTrace();
                        this.logger.error(var24.getMessage(), var24);
                    }
                }
            }

            if(!fileList.isEmpty()) {
                if(dataVersion < 1) {
                    HashMap var25 = new HashMap();
                 /*   var25.put("unitId", Long.valueOf(this.getCurrentUnitId(request)));*/
                    var25.put("dataId", Long.valueOf(dataId));
                    var25.put("dataCode", fileUploadCode.value());
                    dataVersion2 = dataVersion < 1?0:dataVersion - 1;
                    var25.put("dataVersion", Integer.valueOf(dataVersion2));
                    var25.put("dataType", ((FileData)fileList.get(0)).getDataType() + "");
                    this.fileDataDao.updateByDataSource(var25);
                }

                this.fileDataDao.addBatch(fileList);
                return fileList;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}

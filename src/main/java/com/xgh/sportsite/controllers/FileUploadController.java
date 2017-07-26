//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xgh.sportsite.controllers;

import com.xgh.security.Base64Util;
import com.xgh.sportsite.basic.BaseController;
import com.xgh.sportsite.util.ConstantUtil;
import com.xgh.util.DateUtil;
import com.xgh.util.FileUtil;
import com.xgh.util.JSONUtil;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.portlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Scope("prototype")
@RequestMapping({"/common/upload"})
public class FileUploadController extends BaseController {
    private static final Logger logger = Logger.getLogger(FileUploadController.class);

    public FileUploadController() {
    }

    @RequestMapping({"/{methodName}"})
    public ModelAndView uploadInit(@PathVariable("methodName") String methodName) {
        if(!"uploadInit".equals(methodName) && !"imageUploadInit".equals(methodName)) {
            return null;
        } else {
            ModelAndView view = new ModelAndView();
            view.setViewName("common/upload/" + methodName);
            Enumeration names = this.request.getParameterNames();

            while(names.hasMoreElements()) {
                String name = (String)names.nextElement();
                this.request.setAttribute(name, this.request.getParameter(name));
            }

            return view;
        }
    }

    @RequestMapping(
            value = {"/doUpload"},
            method = {RequestMethod.POST}
    )
    public void upload(HttpServletRequest request, HttpServletResponse response) {
        ArrayList fileList = new ArrayList();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        Iterator fileNames = multipartRequest.getFileNames();

        for(int i = 0; fileNames.hasNext(); ++i) {
            String name = (String)fileNames.next();
            MultipartFile myfile = multipartRequest.getFile(name);
            if(myfile.isEmpty()) {
                logger.info("文件未上传");
            } else {
                String OriginalFileName = myfile.getOriginalFilename();
                String saveName = DateUtil.getSystemTime().getTime() + "" + i + OriginalFileName.substring(OriginalFileName.lastIndexOf("."), OriginalFileName.length());
                SimpleDateFormat formatdate = new SimpleDateFormat("yyyy/MM/dd/");
                String relative_path = formatdate.format(new Date());
                String serverPath = ConstantUtil.SERVER_URL;
                String realPath = ConstantUtil.TEMP_PATH + "/" + relative_path;
                File filePath = new File(realPath);
                if(!filePath.exists()) {
                    filePath.mkdirs();
                }

                try {
                    FileUtil.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, saveName));
                } catch (IOException var18) {
                    var18.printStackTrace();
                    logger.error(var18.getMessage(), var18);
                }

                HashMap fileData = new HashMap();
                String uuId = UUID.randomUUID().toString();
                fileData.put("uuId", uuId);
                fileData.put("fileSize", Long.valueOf(myfile.getSize()));
                fileData.put("fileName", saveName);
                fileData.put("oldName", myfile.getOriginalFilename());
                fileData.put("ord", Integer.valueOf(i));
                fileData.put("savePath", Base64Util.encodeToString(realPath + saveName));
                fileData.put("url", serverPath + "/temp_path/" + relative_path + saveName);
                fileList.add(fileData);
            }
        }

        this.out(JSONUtil.getJson(fileList));
    }

    @RequestMapping(
            value = {"/doUploadImage"},
            method = {RequestMethod.POST}
    )
    public void uploadImage(HttpServletRequest request, HttpServletResponse response) {
        ArrayList fileList = new ArrayList();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        Iterator fileNames = multipartRequest.getFileNames();

        for(int i = 1; fileNames.hasNext(); ++i) {
            String dataMap = (String)fileNames.next();
            MultipartFile myfile = multipartRequest.getFile(dataMap);
            if(myfile.isEmpty()) {
                logger.info("文件未上传");
            } else {
                String saveName = DateUtil.getSystemTime().getTime() + "logo_" + i + "_" + ".jpg";
                SimpleDateFormat formatdate = new SimpleDateFormat("yyyy/MM/dd/");
                String relative_path = formatdate.format(new Date());
                String serverPath = ConstantUtil.SERVER_URL;
                String realPath = ConstantUtil.TEMP_PATH + "/" + relative_path;
                File filePath = new File(realPath);
                if(!filePath.exists()) {
                    filePath.mkdirs();
                }

                try {
                    FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, saveName));
                } catch (IOException var17) {
                    var17.printStackTrace();
                    logger.error(var17.getMessage(), var17);
                }

                HashMap fileData = new HashMap();
                String uuId = UUID.randomUUID().toString();
                fileData.put("uuId", uuId);
                fileData.put("fileSize", Long.valueOf(myfile.getSize()));
                fileData.put("fileName", saveName);
                fileData.put("oldName", myfile.getOriginalFilename() + i + ".jpg");
                fileData.put("ord", Integer.valueOf(i));
                fileData.put("savePath", Base64Util.encodeToString(realPath + "/" + saveName));
                fileData.put("url", serverPath + "/temp_path/" + relative_path + "/" + saveName);
                fileList.add(fileData);
            }
        }

        HashMap var18 = new HashMap();
        var18.put("success", Boolean.valueOf(true));
        var18.put("dataList", fileList);
        this.outJson(var18);
    }
}

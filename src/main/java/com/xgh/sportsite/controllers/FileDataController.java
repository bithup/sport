package com.xgh.sportsite.controllers;

import com.xgh.sportsite.basic.BaseController;
import com.xgh.sportsite.services.IFileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * Created by BSX on 2017/4/25.
 */
@Controller
@Scope("prototype")
@RequestMapping("/fileData/")
public class FileDataController extends BaseController {

    @Autowired
    protected IFileDataService fileDataService;

    @RequestMapping(value="deleteFileData")
    public void deleteFileData(){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        String ids = request.getParameter("id");
        String[] ids_ = ids.split("#");
        List<String> list = new ArrayList<String>(Arrays.asList(ids_));
        int flag = fileDataService.updateBatch(list);
        if(flag>0){
            resultMap = getResultMap("1","删除成功");
        }else{
            resultMap = getResultMap("0","删除失败，请重试");
        }
        outJson(resultMap);
    }

}

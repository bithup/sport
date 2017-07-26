//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xgh.sportsite.services;


import com.xgh.sportsite.entity.FileData;
import com.xgh.sportsite.util.ConstantUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IFileDataService {
    int add(FileData var1);

    int delete(long var1);

    int updateByDataSource(Map<String, Object> var1);

    int update(FileData var1);

    FileData get(long var1);

    List<FileData> getList(Map<String, Object> var1);

    List<FileData> getListPage(Map<String, Object> var1);

    long getRows(Map<String, Object> var1);

    List<Map<String, Object>> getFileDatas(Map<String, Object> var1);

    List<FileData> saveFiles(HttpServletRequest var1, String[] var2, long var3, ConstantUtil.FileUploadCode var5, int var6);
    /**
     * 根据id批量删除文件
     * @param list
     * @return
     */
    int updateBatch(List<String> list);
}


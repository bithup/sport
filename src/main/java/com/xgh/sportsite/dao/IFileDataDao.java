//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xgh.sportsite.dao;


import com.xgh.sportsite.entity.FileData;

import java.util.List;
import java.util.Map;

public interface IFileDataDao {
    int add(FileData var1);

    int update(FileData var1);

    int deleteById(long var1);

    int updateByDataSource(Map<String, Object> var1);

    int addBatch(List<FileData> var1);

    FileData get(long var1);

    List<FileData> getList(Map<String, Object> var1);

    List<FileData> getListPage(Map<String, Object> var1);

    long getRows(Map<String, Object> var1);
    /**
     * 根据id批量删除文件
     * @param list
     * @return
     */
    int updateBatch(List<String> list);
}

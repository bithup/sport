//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xgh.sportsite.dao.read;


import com.xgh.sportsite.entity.FileData;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface IFileDataDaoR {
    FileData get(long var1);

    FileData getByNid(long var1);

    List<FileData> getList(Map<String, Object> var1);

    List<FileData> getListPage(Map<String, Object> var1);

    long getRows(Map<String, Object> var1);
}

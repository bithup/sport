//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xgh.sportsite.dao;


import com.xgh.sportsite.dao.read.IFileDataDaoR;
import com.xgh.sportsite.dao.write.IFileDataDaoW;
import com.xgh.sportsite.entity.FileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("fileDataDao")
public class FileDataDaoImpl implements IFileDataDao {
    @Autowired
    protected IFileDataDaoR fileDataDaoR;
    @Autowired
    protected IFileDataDaoW fileDataDaoW;

    public FileDataDaoImpl() {
    }

    public int add(FileData fileData) {
        return this.fileDataDaoW.add(fileData);
    }

    public int update(FileData fileData) {
        return this.fileDataDaoW.update(fileData);
    }

    public int deleteById(long id) {
        return this.fileDataDaoW.deleteById(id);
    }

    public int updateByDataSource(Map<String, Object> map) {
        return this.fileDataDaoW.updateByDataSource(map);
    }

    public int addBatch(List<FileData> list) {
        return this.fileDataDaoW.addBatch(list);
    }

    public FileData get(long id) {
        return this.fileDataDaoR.get(id);
    }

    public List<FileData> getList(Map<String, Object> map) {
        return this.fileDataDaoR.getList(map);
    }

    public List<FileData> getListPage(Map<String, Object> map) {
        return this.fileDataDaoR.getListPage(map);
    }

    public long getRows(Map<String, Object> map) {
        return this.fileDataDaoR.getRows(map);
    }
    public int updateBatch(List<String> list) {
        return fileDataDaoW.updateBatch(list);
    }
}

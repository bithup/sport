package com.xgh.sportsite.services;

import com.xgh.sportsite.dao.ISubjectDao;
import com.xgh.sportsite.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/7.
 */
@Service("subjectService")
public class SubjectServiceImpl implements ISubjectService {

    @Autowired
    protected ISubjectDao subjectDao;

    public List<Subject> getList(Map<String, Object> map) {
        return subjectDao.getList(map);
    }
}

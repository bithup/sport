package com.xgh.sportsite.dao;

import com.xgh.sportsite.dao.read.ISubjectDaoR;
import com.xgh.sportsite.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/7.
 */
@Service("subjectDao")
public class SubjectDaoImpl implements ISubjectDao{

    @Autowired
    protected ISubjectDaoR subjectDaoR;

    public Subject get(long id) {
        return subjectDaoR.get(id);
    }

    public List<Subject> getList(Map<String, Object> map) {
        return subjectDaoR.getList(map);
    }
}

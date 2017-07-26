package com.xgh.sportsite.services;

import com.xgh.sportsite.entity.Subject;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/7.
 */
public interface ISubjectService {

    public List<Subject> getList(Map<String, Object> map);
}

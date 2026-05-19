package com.lintech.service.admin;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lintech.dao.LogRepository;
import com.lintech.entity.Log;

@Service
@Transactional(readOnly = true)
public class LogService {

    @Autowired
    private LogRepository logRepository;

    @Transactional
    public void save(Log log) {
        logRepository.save(log);
    }

    @Transactional
    public void delete(Serializable id) {
        logRepository.deleteById((Integer) id);
    }

    @Transactional
    public void truncate() {
        logRepository.deleteAll();
    }

    @Transactional
    public void update(Log log) {
        logRepository.save(log);
    }

    public Log findOne(Serializable id) {
        return logRepository.findById((Integer) id).orElse(null);
    }

    public List<Log> findAll() {
        return logRepository.findAll();
    }

    public List<Log> findAll(Map<String, Object> params) {
        return logRepository.findAll();
    }

    public Page<Log> findAll(Pageable pageable) {
        return logRepository.findAll(pageable);
    }

}

package com.lintech.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lintech.dao.FunctionRepository;
import com.lintech.entity.Function;


@Service
@Transactional(readOnly = true)
public class FunctionService {
    @Autowired
    FunctionRepository functionRepository;

    @Transactional
    public void save(Function function){
        functionRepository.save(function);
    }

    @Transactional
    public void delete(int id){
        functionRepository.deleteById(id);
    }

    @Transactional
    public void update(Function function){
        functionRepository.save(function);
    }

    public Function findOne(String id){
        return functionRepository.findById(Integer.parseInt(id)).orElse(null);
    }

    public List<Function> findAll(){
        return functionRepository.findAll();
    }

    public List<Function> findAll(Map<String, Object> params){
        return functionRepository.findAll();
    }

    public Page<Function> findAll(Pageable pageable){
        return functionRepository.findAll(pageable);
    }
}

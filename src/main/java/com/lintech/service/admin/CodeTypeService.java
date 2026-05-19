package com.lintech.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lintech.dao.CodeTypeRepository;
import com.lintech.entity.CodeType;


@Service
@Transactional(readOnly = true)
public class CodeTypeService {
    @Autowired
    CodeTypeRepository codeTypeRepository;

    @Transactional
    public void save(CodeType codeType){
        codeTypeRepository.save(codeType);
    }

    @Transactional
    public void delete(int id){
        codeTypeRepository.deleteById(id);
    }

    @Transactional
    public void update(CodeType codeType){
        codeTypeRepository.save(codeType);
    }

    public CodeType findOne(String id){
        return codeTypeRepository.findById(Integer.parseInt(id)).orElse(null);
    }

    public List<CodeType> findAll(){
        return codeTypeRepository.findAll();
    }

    public List<CodeType> findAll(Map<String, Object> params){
        return codeTypeRepository.findAll();
    }

    public Page<CodeType> findAll(Pageable pageable){
        return codeTypeRepository.findAll(pageable);
    }
}

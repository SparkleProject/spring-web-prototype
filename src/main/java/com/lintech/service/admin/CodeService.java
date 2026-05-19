package com.lintech.service.admin;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lintech.core.easyui.Combobox;
import com.lintech.dao.CodeRepository;
import com.lintech.entity.Code;


@Service
@Transactional(readOnly = true)
public class CodeService {
    @Autowired
    CodeRepository codeRepository;

    @Transactional
    public void save(Code code){
        codeRepository.save(code);
    }

    @Transactional
    public void delete(int id){
        codeRepository.deleteById(id);
    }

    @Transactional
    public void update(Code code){
        codeRepository.save(code);
    }

    public Code findOne(String id){
        return codeRepository.findById(Integer.parseInt(id)).orElse(null);
    }

    public List<Code> findAll(){
        return codeRepository.findAll();
    }

    public List<Code> findAll(Map<String, Object> params){
        return codeRepository.findAll();
    }

    public Page<Code> findAll(Pageable pageable){
        return codeRepository.findAll(pageable);
    }

    public List<Combobox> findCombobox(String type, boolean appendFirst) {
        // TODO: Implement with JPA query if needed
        return Collections.emptyList();
    }

    public List<Combobox> findCombobox(String type, boolean appendFirst, String select) {
        // TODO: Implement with JPA query if needed
        return Collections.emptyList();
    }
}

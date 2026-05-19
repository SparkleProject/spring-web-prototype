package com.lintech.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lintech.dao.RoleResRepository;
import com.lintech.entity.RoleRes;


@Service
@Transactional(readOnly = true)
public class RoleResService {
    @Autowired
    RoleResRepository roleResRepository;

    @Transactional
    public void save(RoleRes roleRes){
        roleResRepository.save(roleRes);
    }

    @Transactional
    public void saveBatch(List<RoleRes> roleReses){
        for(RoleRes r : roleReses){
            save(r);
        }
    }

    @Transactional
    public void delete(int id){
        roleResRepository.deleteById(id);
    }

    @Transactional
    public void deleteByClause(Map<String, Object> params){
        Object roleId = params.get("roleId");
        if (roleId != null) {
            roleResRepository.deleteByRoleId(Integer.parseInt(roleId.toString()));
        }
    }

    @Transactional
    public void update(RoleRes roleRes){
        roleResRepository.save(roleRes);
    }

    public RoleRes findOne(String id){
        return roleResRepository.findById(Integer.parseInt(id)).orElse(null);
    }

    public List<RoleRes> findAll(){
        return roleResRepository.findAll();
    }

    public List<RoleRes> findAll(Map<String, Object> params){
        Object roleId = params.get("roleId");
        if (roleId != null) {
            return roleResRepository.findByRoleIdWithResCode(Integer.parseInt(roleId.toString()));
        }
        return roleResRepository.findAll();
    }

    public Page<RoleRes> findAll(Pageable pageable){
        return roleResRepository.findAll(pageable);
    }
}

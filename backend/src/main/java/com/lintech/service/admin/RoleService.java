package com.lintech.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lintech.dao.RoleRepository;
import com.lintech.dao.RoleResRepository;
import com.lintech.entity.Role;


@Service
@Transactional(readOnly = true)
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleResRepository roleResRepository;

    @Transactional
    public void save(Role role){
        roleRepository.save(role);
    }

    @Transactional
    public void delete(int id){
        roleRepository.deleteById(id);
        // sync delete from role_res
        roleResRepository.deleteByRoleId(id);
    }

    @Transactional
    public void update(Role role){
        roleRepository.save(role);
    }

    public Role findOne(String id){
        return roleRepository.findById(Integer.parseInt(id)).orElse(null);
    }

    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    public List<Role> findAll(Map<String, Object> params){
        return roleRepository.findAll();
    }

    public Page<Role> findAll(Pageable pageable){
        return roleRepository.findAll(pageable);
    }
}

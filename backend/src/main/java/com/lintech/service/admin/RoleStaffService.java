package com.lintech.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lintech.dao.RoleStaffRepository;
import com.lintech.entity.RoleStaff;


@Service
@Transactional(readOnly = true)
public class RoleStaffService {
    @Autowired
    private RoleStaffRepository roleStaffRepository;

    @Transactional
    public void save(RoleStaff roleStaff){
        roleStaffRepository.save(roleStaff);
    }

    @Transactional
    public void delete(int id){
        roleStaffRepository.deleteById(id);
    }

    @Transactional
    public void deleteByClause(Map<String, Object> params){
        Object staffId = params.get("staffId");
        Object roleId = params.get("roleId");
        if (staffId != null) {
            roleStaffRepository.deleteByStaffId(Integer.parseInt(staffId.toString()));
        } else if (roleId != null) {
            roleStaffRepository.deleteByRoleId(Integer.parseInt(roleId.toString()));
        }
    }

    @Transactional
    public void update(RoleStaff roleStaff){
        roleStaffRepository.save(roleStaff);
    }

    public RoleStaff findOne(String id){
        return roleStaffRepository.findById(Integer.parseInt(id)).orElse(null);
    }

    public List<RoleStaff> findAll(){
        return roleStaffRepository.findAll();
    }

    public List<RoleStaff> findAll(Map<String, Object> params){
        return roleStaffRepository.findAll();
    }

    public Page<RoleStaff> findAll(Pageable pageable){
        return roleStaffRepository.findAll(pageable);
    }
}

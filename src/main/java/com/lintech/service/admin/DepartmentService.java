package com.lintech.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lintech.dao.DepartmentRepository;
import com.lintech.entity.Department;


@Service
@Transactional(readOnly = true)
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Transactional
    public void save(Department department){
        departmentRepository.save(department);
    }

    @Transactional
    public void delete(int id){
        departmentRepository.deleteById(id);
    }

    @Transactional
    public void update(Department department){
        departmentRepository.save(department);
    }

    public Department findOne(String id){
        return departmentRepository.findById(Integer.parseInt(id)).orElse(null);
    }

    public List<Department> findAll(){
        return departmentRepository.findAll();
    }

    public List<Department> findAll(Map<String, Object> params){
        return departmentRepository.findAll();
    }

    public Page<Department> findAll(Pageable pageable){
        return departmentRepository.findAll(pageable);
    }
}

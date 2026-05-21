package com.lintech.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lintech.dao.StaffLoginRepository;
import com.lintech.entity.StaffLogin;

@Service
@Transactional(readOnly = true)
public class StaffLoginService {
    @Autowired
    StaffLoginRepository staffLoginRepository;

    @Transactional
    public void save(StaffLogin staffLogin){
        staffLoginRepository.save(staffLogin);
    }

    @Transactional
    public void update(StaffLogin staffLogin){
        staffLoginRepository.save(staffLogin);
    }

    public StaffLogin findOne(String id){
        return staffLoginRepository.findById(Integer.parseInt(id)).orElse(null);
    }

    public Page<StaffLogin> findAll(Pageable pageable){
        return staffLoginRepository.findAllWithStaffName(pageable);
    }
}

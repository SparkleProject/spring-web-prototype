package com.lintech.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lintech.dao.StaffRepository;
import com.lintech.entity.Staff;

@Service
@Transactional(readOnly = true)
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;

    @Transactional
    public void save(Staff staff){
        staffRepository.save(staff);
    }

    @Transactional
    public void delete(int id){
        staffRepository.deleteById(id);
    }

    @Transactional
    public void update(Staff staff){
        staffRepository.save(staff);
    }

    public Staff findOne(Integer id){
        return staffRepository.findById(id).orElse(null);
    }

    public List<Staff> findAll(){
        return staffRepository.findAll();
    }

    public List<Staff> findAll(Map<String, Object> params){
        return staffRepository.findAll();
    }

    public Page<Staff> findAll(Pageable pageable){
        return staffRepository.findAll(pageable);
    }

    @Transactional
    public void changePassword(Integer id, String password){
        staffRepository.changePassword(id, password);
    }

    @Transactional
    public void changeEanbled(String id, Integer enabled){
        staffRepository.changeEnabled(Integer.parseInt(id), enabled);
    }

    public Staff findOneByLoginName(String loginName){
        return staffRepository.findByLoginName(loginName).orElse(null);
    }
}

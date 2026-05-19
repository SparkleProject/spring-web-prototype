package com.lintech.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lintech.dao.UserLoginRepository;
import com.lintech.entity.UserLogin;

@Service
@Transactional(readOnly = true)
public class UserLoginService {

    @Autowired
    UserLoginRepository userLoginRepository;

    @Transactional
    public void save(UserLogin userLogin){
        userLoginRepository.save(userLogin);
    }

    @Transactional
    public void update(UserLogin userLogin){
        userLoginRepository.save(userLogin);
    }

    @Transactional
    public void clearActive(String userId){
        userLoginRepository.clearState(userId);
    }

    public Long countAll(Map<String, Object> params){
        return userLoginRepository.count();
    }

    public UserLogin findOne(Integer id){
        return userLoginRepository.findById(id).orElse(null);
    }

    public List<UserLogin> findAll(Map<String, Object> params){
        return userLoginRepository.findAll();
    }

    public Page<UserLogin> findAll(Pageable pageable){
        return userLoginRepository.findAll(pageable);
    }

}

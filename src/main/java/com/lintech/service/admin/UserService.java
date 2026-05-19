package com.lintech.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lintech.dao.UserRepository;
import com.lintech.entity.User;

@Service
@Transactional(readOnly = true)
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void save(User user){
        userRepository.save(user);
    }

    @Transactional
    public void delete(int id){
        userRepository.deleteById(id);
    }

    @Transactional
    public void update(User user){
        userRepository.save(user);
    }

    public User findOne(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public List<User> findAll(Map<String, Object> params){
        return userRepository.findAll();
    }

    public Page<User> findAll(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    @Transactional
    public void changePassword(Integer id, String password){
        userRepository.changePassword(id, password);
    }

    @Transactional
    public void changeEanbled(String id, Integer enabled){
        userRepository.changeEnabled(Integer.parseInt(id), enabled);
    }

    public User findOneByLoginName(String loginName){
        return userRepository.findByLoginName(loginName).orElse(null);
    }
}

package com.lintech.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lintech.core.easyui.Menu;
import com.lintech.dao.MenuRepository;


@Service
@Transactional(readOnly = true)
public class MenuService {
    @Autowired
    MenuRepository menuRepository;

    @Transactional
    public void save(Menu menu){
        menuRepository.save(menu);
    }

    @Transactional
    public void delete(int id){
        menuRepository.deleteById(id);
    }

    @Transactional
    public void update(Menu menu){
        menuRepository.save(menu);
    }

    public Menu findOne(String id){
        return menuRepository.findById(Integer.parseInt(id)).orElse(null);
    }

    public List<Menu> findAll(){
        return menuRepository.findAll();
    }

    public List<Menu> findAll(Map<String, Object> params){
        return menuRepository.findAll();
    }

    public Page<Menu> findAll(Pageable pageable){
        return menuRepository.findAll(pageable);
    }

    public List<Menu> findAllByStaffId(int staffId){
        return menuRepository.findAllByStaffId(staffId);
    }

    public List<Menu> findAllByRole(String roleCode){
        return menuRepository.findAllByRoleCode(roleCode);
    }
}

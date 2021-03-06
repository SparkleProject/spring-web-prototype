package com.lintech.dao;

import java.util.List;
import java.util.Map;

import com.lintech.core.mybatis.Dao;
import com.lintech.core.mybatis.MyBatis;
import com.lintech.entity.Staff;


@MyBatis
public interface StaffDao extends Dao<Staff>{
    /**
     *  修改密码
     * @param id 员工Id
     * @param password 新密码
     */
    void changePassword(Map<String,Object> params);
    
    /**
     * 开启、禁用员工账号
     * @param params
     */
    void changeEanbled(Map<String,Object> params);
    
    Staff findOneByLoginName(String loginName);
    
    List<Map<String,String>> findAllMap();
}

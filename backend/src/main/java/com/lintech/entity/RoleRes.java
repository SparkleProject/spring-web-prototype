package com.lintech.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "t_role_res")
public class RoleRes {
    public static final int RES_TYPE_MENU=1;
    public static final int RES_TYPE_FUNCTION=2;

    public RoleRes() {}

    public RoleRes(Integer id, Integer roleId, Integer resType, Integer resId, String resCode) {
        this.id = id;
        this.roleId = roleId;
        this.resType = resType;
        this.resId = resId;
        this.resCode = resCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer roleId;

    private Integer resType;

    private Integer resId;
    
    //extend for spring security
    @Transient
    private String roleCode;

    @Transient
    private String resCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResType() {
        return resType;
    }

    public void setResType(Integer resType) {
        this.resType = resType;
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }
    

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }
}
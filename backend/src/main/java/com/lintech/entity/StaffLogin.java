package com.lintech.entity;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "t_staff_login")
public class StaffLogin {

    public StaffLogin() {}

    public StaffLogin(Integer id, Integer staffId, Date loginDate, String loginIp, String staffName) {
        this.id = id;
        this.staffId = staffId;
        this.loginDate = loginDate;
        this.loginIp = loginIp;
        this.staffName = staffName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer staffId;

    private Date loginDate;

    private String loginIp;
    
    //extend
    @Transient
    private String staffName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    //@JsonSerialize(using = CustomDateSerializer.class)
    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
}
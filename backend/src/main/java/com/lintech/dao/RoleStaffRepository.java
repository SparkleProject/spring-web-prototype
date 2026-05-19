package com.lintech.dao;

import com.lintech.entity.RoleStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleStaffRepository extends JpaRepository<RoleStaff, Integer>, JpaSpecificationExecutor<RoleStaff> {

    void deleteByRoleId(Integer roleId);

    void deleteByStaffId(Integer staffId);
}

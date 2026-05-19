package com.lintech.dao;

import com.lintech.core.easyui.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer>, JpaSpecificationExecutor<Menu> {

    @Query("SELECT m FROM Menu m WHERE m.id IN (SELECT rr.resId FROM RoleRes rr WHERE rr.resType = 1 AND rr.roleId IN (SELECT rs.roleId FROM RoleStaff rs WHERE rs.staffId = :staffId)) ORDER BY m.seq")
    List<Menu> findAllByStaffId(@Param("staffId") Integer staffId);

    @Query("SELECT m FROM Menu m WHERE m.id IN (SELECT rr.resId FROM RoleRes rr WHERE rr.resType = 1 AND rr.roleId IN (SELECT r.id FROM Role r WHERE r.code = :roleCode)) ORDER BY m.seq")
    List<Menu> findAllByRoleCode(@Param("roleCode") String roleCode);
}

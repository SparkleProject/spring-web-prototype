package com.lintech.dao;

import com.lintech.entity.RoleRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleResRepository extends JpaRepository<RoleRes, Integer>, JpaSpecificationExecutor<RoleRes> {

    void deleteByRoleId(Integer roleId);
}

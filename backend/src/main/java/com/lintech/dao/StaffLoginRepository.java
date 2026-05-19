package com.lintech.dao;

import com.lintech.entity.StaffLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffLoginRepository extends JpaRepository<StaffLogin, Integer>, JpaSpecificationExecutor<StaffLogin> {
}

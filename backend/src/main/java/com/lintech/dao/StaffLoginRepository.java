package com.lintech.dao;

import com.lintech.entity.StaffLogin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffLoginRepository extends JpaRepository<StaffLogin, Integer>, JpaSpecificationExecutor<StaffLogin> {

    @Query("""
        SELECT new StaffLogin(sl.id, sl.staffId, sl.loginDate, sl.loginIp, s.name)
        FROM StaffLogin sl LEFT JOIN Staff s ON sl.staffId = s.id
        ORDER BY sl.loginDate DESC
        """)
    Page<StaffLogin> findAllWithStaffName(Pageable pageable);
}

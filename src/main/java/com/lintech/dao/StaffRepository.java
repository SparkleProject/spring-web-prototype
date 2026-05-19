package com.lintech.dao;

import com.lintech.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer>, JpaSpecificationExecutor<Staff> {

    Optional<Staff> findByLoginName(String loginName);

    @Modifying
    @Query("UPDATE Staff s SET s.password = :password WHERE s.id = :id")
    void changePassword(@Param("id") Integer id, @Param("password") String password);

    @Modifying
    @Query("UPDATE Staff s SET s.enabled = :enabled WHERE s.id = :id")
    void changeEnabled(@Param("id") Integer id, @Param("enabled") Integer enabled);
}

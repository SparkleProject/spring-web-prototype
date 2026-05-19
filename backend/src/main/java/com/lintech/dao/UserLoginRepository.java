package com.lintech.dao;

import com.lintech.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Integer>, JpaSpecificationExecutor<UserLogin> {

    @Modifying
    @Query("UPDATE UserLogin ul SET ul.state = 0 WHERE ul.userId = :userId")
    void clearState(@Param("userId") String userId);
}

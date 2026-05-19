package com.lintech.dao;

import com.lintech.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    Optional<User> findByLoginName(String loginName);

    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.id = :id")
    void changePassword(@Param("id") Integer id, @Param("password") String password);

    @Modifying
    @Query("UPDATE User u SET u.enabled = :enabled WHERE u.id = :id")
    void changeEnabled(@Param("id") Integer id, @Param("enabled") Integer enabled);
}

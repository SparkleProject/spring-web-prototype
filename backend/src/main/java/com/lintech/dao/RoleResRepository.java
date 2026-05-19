package com.lintech.dao;

import java.util.List;

import com.lintech.entity.RoleRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleResRepository extends JpaRepository<RoleRes, Integer>, JpaSpecificationExecutor<RoleRes> {

    void deleteByRoleId(Integer roleId);

    @Query("""
        SELECT new RoleRes(rr.id, rr.roleId, rr.resType, rr.resId,
            CASE WHEN rr.resType = 1 THEN m.url WHEN rr.resType = 2 THEN f.code ELSE '' END)
        FROM RoleRes rr
        LEFT JOIN Menu m ON rr.resType = 1 AND rr.resId = m.id
        LEFT JOIN Function f ON rr.resType = 2 AND rr.resId = f.id
        WHERE rr.roleId = :roleId
        """)
    List<RoleRes> findByRoleIdWithResCode(Integer roleId);
}

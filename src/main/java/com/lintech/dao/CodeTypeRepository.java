package com.lintech.dao;

import com.lintech.entity.CodeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeTypeRepository extends JpaRepository<CodeType, Integer>, JpaSpecificationExecutor<CodeType> {
}

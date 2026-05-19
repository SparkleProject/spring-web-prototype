package com.lintech.dao;

import com.lintech.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>, JpaSpecificationExecutor<Task> {

    @Modifying
    @Query("UPDATE Task t SET t.state = :state WHERE t.id = :id")
    void changeState(@Param("id") Integer id, @Param("state") Integer state);

    @Modifying
    @Query("UPDATE Task t SET t.state = :state")
    void changeAllState(@Param("state") Integer state);
}

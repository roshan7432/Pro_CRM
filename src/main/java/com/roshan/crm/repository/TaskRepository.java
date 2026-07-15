package com.roshan.crm.repository;

import com.roshan.crm.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignedToId(Long userId);
    List<Task> findByLeadId(Long leadId);
    List<Task> findByDealId(Long dealId);
}

package com.roshan.crm.repository;

import com.roshan.crm.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {
    List<Deal> findByAssignedToId(Long userId);
    List<Deal> findByLeadId(Long leadId);
}

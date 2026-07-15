package com.roshan.crm.repository;

import com.roshan.crm.entity.Lead;
import com.roshan.crm.entity.LeadStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeadRepository
        extends JpaRepository<Lead, Long>
{
    List<Lead> findByNameContainingIgnoreCase(String name);
    List<Lead> findByStatus(LeadStatus status);
    long countByStatus(LeadStatus status);
    List<Lead> findByAssignedToId(Long userId);
}

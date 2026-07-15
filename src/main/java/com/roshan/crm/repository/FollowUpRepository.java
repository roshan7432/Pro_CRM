package com.roshan.crm.repository;

import com.roshan.crm.entity.FollowUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowUpRepository extends JpaRepository<FollowUp, Long> {
    List<FollowUp> findByLeadId(Long leadId);
    List<FollowUp> findByUserId(Long userId);
}

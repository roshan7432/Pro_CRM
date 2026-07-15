package com.roshan.crm.service;

import com.roshan.crm.entity.FollowUp;
import java.util.List;

public interface FollowUpService {
    FollowUp createFollowUp(FollowUp followUp);
    FollowUp getFollowUpById(Long id);
    List<FollowUp> getAllFollowUps();
    FollowUp updateFollowUp(Long id, FollowUp followUp);
    void deleteFollowUp(Long id);
    List<FollowUp> getFollowUpsByLeadId(Long leadId);
}

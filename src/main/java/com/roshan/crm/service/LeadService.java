package com.roshan.crm.service;

import com.roshan.crm.entity.Lead;
import com.roshan.crm.entity.LeadStatus;

import java.util.List;

public interface LeadService {

    Lead createLead(Lead lead);

    List<Lead> getAllLeads();

    Lead getLeadById(Long id);

    Lead updateLead(Long id, Lead lead);

    void deleteLead(Long id);

    List<Lead> searchLead(String name);

    List<Lead> getLeadsByStatus(LeadStatus status);

    List<Lead> getAssignedLeads(Long userId);
}

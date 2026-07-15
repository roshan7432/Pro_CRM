package com.roshan.crm.service.impl;

import com.roshan.crm.entity.Lead;
import com.roshan.crm.entity.LeadStatus;
import com.roshan.crm.entity.User;
import com.roshan.crm.exception.ResourceNotFoundException;
import com.roshan.crm.repository.LeadRepository;
import com.roshan.crm.repository.UserRepository;
import com.roshan.crm.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadServiceImpl implements LeadService
{
    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Lead createLead(Lead lead) {
        if (lead.getAssignedTo() != null && lead.getAssignedTo().getId() != null) {
            User assignedTo = userRepository.findById(lead.getAssignedTo().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Assigned user not found"));
            lead.setAssignedTo(assignedTo);
        }
        return leadRepository.save(lead);
    }

    @Override
    public List<Lead> getAllLeads() {
        return leadRepository.findAll();
    }

    @Override
    public Lead getLeadById(Long id) {
        return leadRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Lead Not Found"));
    }

    @Override
    public Lead updateLead(Long id, Lead leadDetails) {
        Lead existingLead = getLeadById(id);

        existingLead.setName(leadDetails.getName());
        existingLead.setCompany(leadDetails.getCompany());
        existingLead.setSource(leadDetails.getSource());
        existingLead.setPhone(leadDetails.getPhone());
        existingLead.setStatus(leadDetails.getStatus());
        existingLead.setEmail(leadDetails.getEmail());

        if (leadDetails.getAssignedTo() != null && leadDetails.getAssignedTo().getId() != null) {
            User assignedTo = userRepository.findById(leadDetails.getAssignedTo().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Assigned user not found"));
            existingLead.setAssignedTo(assignedTo);
        } else {
            existingLead.setAssignedTo(null);
        }

        return leadRepository.save(existingLead);
    }

    @Override
    public void deleteLead(Long id) {
        leadRepository.deleteById(id);
    }

    @Override
    public List<Lead> searchLead(String name) {
        return leadRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Lead> getLeadsByStatus(LeadStatus status) {
        return leadRepository.findByStatus(status);
    }

    @Override
    public List<Lead> getAssignedLeads(Long userId) {
        return leadRepository.findByAssignedToId(userId);
    }
}

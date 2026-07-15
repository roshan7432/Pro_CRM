package com.roshan.crm.service.impl;

import com.roshan.crm.entity.FollowUp;
import com.roshan.crm.entity.Lead;
import com.roshan.crm.entity.User;
import com.roshan.crm.exception.ResourceNotFoundException;
import com.roshan.crm.repository.FollowUpRepository;
import com.roshan.crm.repository.LeadRepository;
import com.roshan.crm.repository.UserRepository;
import com.roshan.crm.service.FollowUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowUpServiceImpl implements FollowUpService {

    @Autowired
    private FollowUpRepository followUpRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LeadRepository leadRepository;

    @Override
    public FollowUp createFollowUp(FollowUp followUp) {
        if (followUp.getUser() != null && followUp.getUser().getId() != null) {
            User user = userRepository.findById(followUp.getUser().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            followUp.setUser(user);
        }
        if (followUp.getLead() != null && followUp.getLead().getId() != null) {
            Lead lead = leadRepository.findById(followUp.getLead().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Lead not found"));
            followUp.setLead(lead);
        } else {
             throw new IllegalArgumentException("FollowUp must be associated with a Lead");
        }
        return followUpRepository.save(followUp);
    }

    @Override
    public FollowUp getFollowUpById(Long id) {
        return followUpRepository.findById(id).orElseThrow(() -> new RuntimeException("FollowUp not found"));
    }

    @Override
    public List<FollowUp> getAllFollowUps() {
        return followUpRepository.findAll();
    }

    @Override
    public FollowUp updateFollowUp(Long id, FollowUp followUpDetails) {
        FollowUp existingFollowUp = getFollowUpById(id);
        existingFollowUp.setNotes(followUpDetails.getNotes());
        existingFollowUp.setScheduledAt(followUpDetails.getScheduledAt());
        existingFollowUp.setCompleted(followUpDetails.isCompleted());
        
        if (followUpDetails.getUser() != null && followUpDetails.getUser().getId() != null) {
            User user = userRepository.findById(followUpDetails.getUser().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            existingFollowUp.setUser(user);
        } else {
            existingFollowUp.setUser(null);
        }

        if (followUpDetails.getLead() != null && followUpDetails.getLead().getId() != null) {
            Lead lead = leadRepository.findById(followUpDetails.getLead().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Lead not found"));
            existingFollowUp.setLead(lead);
        }

        return followUpRepository.save(existingFollowUp);
    }

    @Override
    public void deleteFollowUp(Long id) {
        followUpRepository.deleteById(id);
    }

    @Override
    public List<FollowUp> getFollowUpsByLeadId(Long leadId) {
        return followUpRepository.findByLeadId(leadId);
    }
}

package com.roshan.crm.service.impl;

import com.roshan.crm.entity.Deal;
import com.roshan.crm.entity.Lead;
import com.roshan.crm.entity.User;
import com.roshan.crm.exception.ResourceNotFoundException;
import com.roshan.crm.repository.DealRepository;
import com.roshan.crm.repository.LeadRepository;
import com.roshan.crm.repository.UserRepository;
import com.roshan.crm.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealServiceImpl implements DealService {

    @Autowired
    private DealRepository dealRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LeadRepository leadRepository;

    @Override
    public Deal createDeal(Deal deal) {
        if (deal.getAssignedTo() != null && deal.getAssignedTo().getId() != null) {
            User assignedTo = userRepository.findById(deal.getAssignedTo().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Assigned user not found"));
            deal.setAssignedTo(assignedTo);
        }
        if (deal.getLead() != null && deal.getLead().getId() != null) {
            Lead lead = leadRepository.findById(deal.getLead().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Lead not found"));
            deal.setLead(lead);
        }
        return dealRepository.save(deal);
    }

    @Override
    public Deal getDealById(Long id) {
        return dealRepository.findById(id).orElseThrow(() -> new RuntimeException("Deal not found"));
    }

    @Override
    public List<Deal> getAllDeals() {
        return dealRepository.findAll();
    }

    @Override
    public Deal updateDeal(Long id, Deal dealDetails) {
        Deal existingDeal = getDealById(id);
        existingDeal.setName(dealDetails.getName());
        existingDeal.setAmount(dealDetails.getAmount());
        existingDeal.setStage(dealDetails.getStage());
        existingDeal.setExpectedCloseDate(dealDetails.getExpectedCloseDate());
        
        if (dealDetails.getAssignedTo() != null && dealDetails.getAssignedTo().getId() != null) {
            User assignedTo = userRepository.findById(dealDetails.getAssignedTo().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Assigned user not found"));
            existingDeal.setAssignedTo(assignedTo);
        } else {
            existingDeal.setAssignedTo(null);
        }

        if (dealDetails.getLead() != null && dealDetails.getLead().getId() != null) {
            Lead lead = leadRepository.findById(dealDetails.getLead().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Lead not found"));
            existingDeal.setLead(lead);
        } else {
            existingDeal.setLead(null);
        }
        
        return dealRepository.save(existingDeal);
    }

    @Override
    public void deleteDeal(Long id) {
        dealRepository.deleteById(id);
    }

    @Override
    public List<Deal> getDealsByUserId(Long userId) {
        return dealRepository.findByAssignedToId(userId);
    }

    @Override
    public List<Deal> getDealsByLeadId(Long leadId) {
        return dealRepository.findByLeadId(leadId);
    }
}

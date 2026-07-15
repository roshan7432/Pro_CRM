package com.roshan.crm.controller;

import com.roshan.crm.dto.DashboardStats;
import com.roshan.crm.entity.Lead;
import com.roshan.crm.entity.LeadStatus;
import com.roshan.crm.repository.LeadRepository;
import com.roshan.crm.service.LeadService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
public class LeadController
{
   private static final Logger logger = LoggerFactory.getLogger(LeadController.class);
   private final LeadService leadService;
   private final LeadRepository leadRepository;

    public LeadController(LeadService leadService, LeadRepository leadRepository) {
        this.leadService = leadService;
        this.leadRepository = leadRepository;
    }

    @PostMapping
    public Lead createLead(@Valid  @RequestBody Lead lead)
    {
        logger.info("Received request to create Lead: {}", lead);
        if (lead.getAssignedTo() != null) {
            logger.info("Lead assigned to user ID: {}", lead.getAssignedTo().getId());
        } else {
            logger.info("Lead is unassigned");
        }
        Lead createdLead = leadService.createLead(lead);
        logger.info("Successfully created Lead with ID: {}", createdLead.getId());
        return createdLead;
    }

    @GetMapping
    public List<Lead> getAllLeads()
    {
        logger.info("Fetching all leads");
        return leadService.getAllLeads();
    }

    @GetMapping("/{id}")
    public Lead getLeadById(@PathVariable Long id)
    {
        return leadService.getLeadById(id);
    }

    @PutMapping("/{id}")
    public Lead updateLead(@PathVariable Long id, @RequestBody Lead lead)
    {
        logger.info("Received request to update Lead {}: {}", id, lead);
        return leadService.updateLead(id,lead);
    }

    @DeleteMapping("/{id}")
    public void deleteLead(@PathVariable Long id)
    {
        leadService.deleteLead(id);
    }
    @GetMapping("/search")
    public List<Lead> searchLead(
            @RequestParam String name) {

        return leadRepository.findByNameContainingIgnoreCase(name);
    }
    @GetMapping("/status")
    public List<Lead> getByStatus(
            @RequestParam LeadStatus status) {

        return leadRepository.findByStatus(status);
    }

    @GetMapping("/assigned")
    public List<Lead> getAssignedLeads(
            @RequestParam Long userId) {

        return leadService.getAssignedLeads(userId);
    }
}

package com.roshan.crm.controller;

import com.roshan.crm.dto.DashboardStats;
import com.roshan.crm.entity.Deal;
import com.roshan.crm.entity.DealStage;
import com.roshan.crm.entity.LeadStatus;
import com.roshan.crm.repository.DealRepository;
import com.roshan.crm.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class DashboardController {

    private final LeadRepository leadRepository;
    private final DealRepository dealRepository;

    @Autowired
    public DashboardController(LeadRepository leadRepository, DealRepository dealRepository) {
        this.leadRepository = leadRepository;
        this.dealRepository = dealRepository;
    }

    @GetMapping("/api/dashboard/stats")
    public DashboardStats getStats() {

        long totalLeads = leadRepository.count();
        long wonLeads = leadRepository.countByStatus(LeadStatus.WON);
        long lostLeads = leadRepository.countByStatus(LeadStatus.LOST);

        List<Deal> deals = dealRepository.findAll();
        
        BigDecimal totalRevenue = deals.stream()
                .filter(deal -> deal.getStage() == DealStage.WON)
                .map(Deal::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<String, Long> pipelineStats = deals.stream()
                .collect(Collectors.groupingBy(deal -> deal.getStage().name(), Collectors.counting()));

        return new DashboardStats(totalLeads, wonLeads, lostLeads, totalRevenue, pipelineStats);
    }
}

package com.roshan.crm.dto;

import java.math.BigDecimal;
import java.util.Map;

public class DashboardStats {

    private long totalLeads;
    private long wonLeads;
    private long lostLeads;
    private BigDecimal totalRevenue;
    private Map<String, Long> pipelineStats;

    public DashboardStats(long totalLeads, long wonLeads, long lostLeads) {
        this.totalLeads = totalLeads;
        this.wonLeads = wonLeads;
        this.lostLeads = lostLeads;
    }

    public DashboardStats(long totalLeads, long wonLeads, long lostLeads, BigDecimal totalRevenue, Map<String, Long> pipelineStats) {
        this.totalLeads = totalLeads;
        this.wonLeads = wonLeads;
        this.lostLeads = lostLeads;
        this.totalRevenue = totalRevenue;
        this.pipelineStats = pipelineStats;
    }

    public long getTotalLeads() {
        return totalLeads;
    }

    public void setTotalLeads(long totalLeads) {
        this.totalLeads = totalLeads;
    }

    public long getWonLeads() {
        return wonLeads;
    }

    public void setWonLeads(long wonLeads) {
        this.wonLeads = wonLeads;
    }

    public long getLostLeads() {
        return lostLeads;
    }

    public void setLostLeads(long lostLeads) {
        this.lostLeads = lostLeads;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Map<String, Long> getPipelineStats() {
        return pipelineStats;
    }

    public void setPipelineStats(Map<String, Long> pipelineStats) {
        this.pipelineStats = pipelineStats;
    }
}

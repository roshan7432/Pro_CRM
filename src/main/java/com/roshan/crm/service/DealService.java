package com.roshan.crm.service;

import com.roshan.crm.entity.Deal;
import java.util.List;

public interface DealService {
    Deal createDeal(Deal deal);
    Deal getDealById(Long id);
    List<Deal> getAllDeals();
    Deal updateDeal(Long id, Deal deal);
    void deleteDeal(Long id);
    List<Deal> getDealsByUserId(Long userId);
    List<Deal> getDealsByLeadId(Long leadId);
}

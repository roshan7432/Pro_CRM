package com.roshan.crm.controller;

import com.roshan.crm.entity.FollowUp;
import com.roshan.crm.service.FollowUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/follow-ups")
public class FollowUpController {

    @Autowired
    private FollowUpService followUpService;

    @PostMapping
    public ResponseEntity<FollowUp> createFollowUp(@Valid @RequestBody FollowUp followUp) {
        return ResponseEntity.ok(followUpService.createFollowUp(followUp));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FollowUp> getFollowUpById(@PathVariable Long id) {
        return ResponseEntity.ok(followUpService.getFollowUpById(id));
    }

    @GetMapping
    public ResponseEntity<List<FollowUp>> getAllFollowUps() {
        return ResponseEntity.ok(followUpService.getAllFollowUps());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FollowUp> updateFollowUp(@PathVariable Long id, @Valid @RequestBody FollowUp followUp) {
        return ResponseEntity.ok(followUpService.updateFollowUp(id, followUp));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFollowUp(@PathVariable Long id) {
        followUpService.deleteFollowUp(id);
        return ResponseEntity.ok().build();
    }
}

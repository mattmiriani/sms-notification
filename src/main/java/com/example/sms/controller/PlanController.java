package com.example.sms.controller;

import com.example.sms.dto.PageableDTO;
import com.example.sms.entity.Plan;
import com.example.sms.service.PlanService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@AllArgsConstructor(onConstructor_ = @__(@Autowired))
@RestController
@RequestMapping("/plans")
public class PlanController {

    private final PlanService planService;

    @GetMapping
    public ResponseEntity<Page<Plan>> findAll(PageableDTO pageableDTO) {
        try {
            return ResponseEntity.ok(planService.findAll(pageableDTO.getPageable()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plan> findById(@PathVariable("id") UUID planId) {
        try {
            return ResponseEntity.ok(planService.findById(planId));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Plan> create(@RequestBody Plan plan) {
        try {
            return ResponseEntity.status(CREATED).body(planService.create(plan));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plan> update(@PathVariable("id") UUID planId,
                                       @RequestBody Plan plan) {
        try {
            return ResponseEntity.ok(planService.update(planId, plan));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID planId) {
        try {
            planService.delete(planId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.example.sms.controller;

import com.example.sms.dto.PageableDTO;
import com.example.sms.dto.PlanDTO;
import com.example.sms.mapper.PlanMapper;
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
    private final PlanMapper planMapper;

    @GetMapping
    public ResponseEntity<Page<PlanDTO>> findAll(PageableDTO pageableDTO) {
        try {
            var response = planService.findAll(pageableDTO.getPageable());

            return ResponseEntity.ok(response.map(planMapper::planToPlanDTO));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanDTO> findById(@PathVariable("id") UUID planId) {
        try {
            var response = planService.findById(planId);

            return ResponseEntity.ok(planMapper.planToPlanDTO(response));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PlanDTO> create(@RequestBody PlanDTO planDTO) {
        try {
            var plan = planMapper.planDTOToPlan(planDTO);
            var response = planService.create(plan);

            return ResponseEntity.status(CREATED).body(planMapper.planToPlanDTO(response));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanDTO> update(@PathVariable("id") UUID planId,
                                          @RequestBody PlanDTO planDTO) {
        try {
            var plan = planMapper.planDTOToPlan(planDTO);
            var response = planService.update(planId, plan);

            return ResponseEntity.ok(planMapper.planToPlanDTO(response));
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

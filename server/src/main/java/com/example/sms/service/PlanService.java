package com.example.sms.service;

import com.example.sms.entity.Plan;
import com.example.sms.exception.SmsException;
import com.example.sms.repository.PlanRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor(onConstructor_ = @__(@Autowired))
@Service
public class PlanService {

    private final PlanRepository planRepository;

    @Transactional(readOnly = true)
    public Page<Plan> findAll(Pageable pageable) {
        return planRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Plan findById(UUID planId) {
        return planRepository.findById(planId).orElseThrow(
                () -> new SmsException(HttpStatus.NOT_FOUND, "Plan not found!")
        );
    }

    private Plan save(Plan plan) {
        return planRepository.save(plan);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Plan create(Plan plan) {
        return this.save(new Plan(plan));
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Plan update(UUID planId, Plan plan) {
        var planToUpdate = this.findById(planId);

        planToUpdate.mergeForUpdate(plan);

        return planRepository.save(planToUpdate);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(UUID planId) {
        var planToDelete = this.findById(planId);

        planToDelete.setUpdatedAt(LocalDateTime.now());
        planToDelete.setActive(false);

        this.save(planToDelete);
    }
}

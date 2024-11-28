package com.example.sms.service;

import com.example.sms.entity.Plan;
import com.example.sms.enumeration.PlanType;
import com.example.sms.exception.SmsException;
import com.example.sms.repository.PlanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlanServiceTest {

    @Mock
    private PlanRepository planRepository;

    @InjectMocks
    private PlanService planService;

    private UUID planId;
    private Plan plan;

    @BeforeEach
    void setUp() {
        planId = UUID.randomUUID();
        plan = new Plan();
        plan.setId(planId);
        plan.setType(PlanType.Credit);
        plan.setMessageAmount(BigDecimal.valueOf(10));
        plan.setCredit(BigDecimal.valueOf(50));
        plan.setActive(true);
        plan.setCreatedAt(LocalDateTime.now());
        plan.setUpdatedAt(LocalDateTime.now());
    }

    @Test
    void testFindAll() {
        Pageable pageable = Pageable.unpaged();
        Page<Plan> page = mock(Page.class);

        when(planRepository.findAll(pageable)).thenReturn(page);

        Page<Plan> result = planService.findAll(pageable);

        assertEquals(page, result);
        verify(planRepository).findAll(pageable);
    }

    @Test
    void testFindByIdWhenPlanExists() {
        when(planRepository.findById(planId)).thenReturn(Optional.of(plan));

        Plan result = planService.findById(planId);

        assertNotNull(result);
        assertEquals(planId, result.getId());
        verify(planRepository).findById(planId);
    }

    @Test
    void testFindByIdWhenPlanDoesNotExist() {
        when(planRepository.findById(planId)).thenReturn(Optional.empty());

        SmsException exception = assertThrows(SmsException.class, () -> planService.findById(planId));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Plan not found!", exception.getMessage());
    }

    @Test
    void testCreate() {
        Plan newPlan = new Plan();
        newPlan.setType(PlanType.Credit);
        newPlan.setMessageAmount(BigDecimal.valueOf(10));
        newPlan.setCredit(BigDecimal.valueOf(50));
        newPlan.setActive(true);

        when(planRepository.save(any(Plan.class))).thenAnswer(invocation -> {
            Plan planToSave = invocation.getArgument(0);
            planToSave.setId(planId);
            return planToSave;
        });

        Plan result = planService.create(newPlan);

        assertNotNull(result);
        assertEquals(planId, result.getId());

        ArgumentCaptor<Plan> planCaptor = ArgumentCaptor.forClass(Plan.class);
        verify(planRepository).save(planCaptor.capture());
        Plan capturedPlan = planCaptor.getValue();

        assertEquals(PlanType.Credit, capturedPlan.getType());
        assertEquals(BigDecimal.valueOf(10), capturedPlan.getMessageAmount());
        assertEquals(BigDecimal.valueOf(50), capturedPlan.getCredit());
        assertTrue(capturedPlan.getActive());
    }

    @Test
    void testUpdate() {
        Plan updatedPlan = new Plan();
        updatedPlan.setId(planId);
        updatedPlan.setActive(true);
        when(planRepository.findById(planId)).thenReturn(Optional.of(plan));
        when(planRepository.save(any(Plan.class))).thenReturn(updatedPlan);

        Plan result = planService.update(planId, updatedPlan);

        assertNotNull(result);
        assertEquals(planId, result.getId());
        verify(planRepository).findById(planId);
        verify(planRepository).save(Mockito.any(Plan.class));
    }

    @Test
    void testDelete() {
        when(planRepository.findById(planId)).thenReturn(Optional.of(plan));
        when(planRepository.save(any(Plan.class))).thenReturn(plan);

        planService.delete(planId);

        assertFalse(plan.getActive());
        assertNotNull(plan.getUpdatedAt());
        verify(planRepository).findById(planId);
        verify(planRepository).save(plan);
    }
}

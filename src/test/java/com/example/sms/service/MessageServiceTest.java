package com.example.sms.service;

import com.example.sms.entity.Customer;
import com.example.sms.entity.Message;
import com.example.sms.entity.Plan;
import com.example.sms.enumeration.PlanType;
import com.example.sms.exception.SmsException;
import com.example.sms.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private CustomerService customerService;

    @Mock
    private PlanService planService;

    @InjectMocks
    private MessageService messageService;

    private UUID customerId;
    private UUID planId;
    private Customer customer;
    private Plan plan;

    @BeforeEach
    void setUp() {
        customerId = UUID.randomUUID();
        planId = UUID.randomUUID();
        customer = new Customer();
        plan = new Plan();

        customer.setId(customerId);
        customer.setPlan(plan);
        customer.setCurrentFunds(BigDecimal.valueOf(100));
        customer.setCredit(BigDecimal.valueOf(50));

        plan.setId(planId);
    }

    @Test
    void testSendWithBalancePlan() {
        plan.setType(PlanType.Balance);
        plan.setMessageAmount(BigDecimal.valueOf(20));

        when(customerService.findById(customerId)).thenReturn(customer);
        when(planService.findById(planId)).thenReturn(plan);
        when(messageRepository.save(any(Message.class))).thenAnswer(invocation -> invocation.getArguments()[0]);

        Message message = new Message();
        message.setCustomer(customer);

        Message result = messageService.send(message);

        assertNotNull(result);
        assertEquals(customer, result.getCustomer());
        verify(customerService).findById(customerId);
        verify(planService).findById(planId);
        verify(messageRepository).save(result);
        assertEquals(BigDecimal.valueOf(80), customer.getCurrentFunds());
    }

    @Test
    void testSendWithCreditPlan() {
        plan.setType(PlanType.Credit);
        plan.setMessageAmount(BigDecimal.valueOf(10));

        when(customerService.findById(customerId)).thenReturn(customer);
        when(planService.findById(planId)).thenReturn(plan);
        when(messageRepository.save(any(Message.class))).thenAnswer(invocation -> invocation.getArguments()[0]);

        Message message = new Message();
        message.setCustomer(customer);

        Message result = messageService.send(message);

        assertNotNull(result);
        assertEquals(customer, result.getCustomer());
        verify(customerService).findById(customerId);
        verify(planService).findById(planId);
        verify(messageRepository).save(result);
        assertEquals(BigDecimal.valueOf(40), customer.getCredit());
    }

    @Test
    void testSendWithInsufficientBalance() {
        plan.setType(PlanType.Balance);
        plan.setMessageAmount(BigDecimal.valueOf(200));

        when(customerService.findById(customerId)).thenReturn(customer);
        when(planService.findById(planId)).thenReturn(plan);

        Message message = new Message();
        message.setCustomer(customer);

        SmsException exception = assertThrows(SmsException.class, () -> messageService.send(message));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        assertEquals("Not enough balance!", exception.getMessage());
    }

    @Test
    void testSendWithInsufficientCredit() {
        plan.setType(PlanType.Credit);
        plan.setMessageAmount(BigDecimal.valueOf(60));

        when(customerService.findById(customerId)).thenReturn(customer);
        when(planService.findById(planId)).thenReturn(plan);

        Message message = new Message();
        message.setCustomer(customer);

        SmsException exception = assertThrows(SmsException.class, () -> messageService.send(message));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        assertEquals("Not enough credit!", exception.getMessage());
    }
}

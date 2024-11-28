package com.example.sms.service;

import com.example.sms.entity.Customer;
import com.example.sms.entity.Plan;
import com.example.sms.exception.SmsException;
import com.example.sms.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.example.sms.enumeration.PlanType.Credit;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PlanService planService;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById_CustomerExists() {
        var customer = new Customer();
        var plan = new Plan();
        plan.setId(UUID.randomUUID());
        plan.setActive(true);
        customer.setId(UUID.randomUUID());
        customer.setPlan(plan);
        customer.setActive(true);
        when(planService.findById(customer.getPlan().getId())).thenReturn(customer.getPlan());
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));

        var result = customerService.findById(customer.getId());
        assertNotNull(result);
        assertTrue(result.getActive());
    }

    @Test
    void testFindById_CustomerNotFound() {
        var customerId = UUID.randomUUID();

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        var thrown = assertThrows(SmsException.class, () -> customerService.findById(customerId));
        assertEquals(HttpStatus.NOT_FOUND, thrown.getStatus());
        assertEquals("Customer not found!", thrown.getMessage());
    }

    @Test
    void testCreate_Success() {
        var customer = new Customer();
        var plan = new Plan();

        plan.setId(UUID.randomUUID());
        plan.setType(Credit);
        plan.setMessageAmount(new BigDecimal(10));
        plan.setCredit(new BigDecimal(100));
        plan.setActive(true);
        plan.setCreatedAt(LocalDateTime.now());
        plan.setUpdatedAt(LocalDateTime.now());

        customer.setId(UUID.randomUUID());
        customer.setName("John Doe");
        customer.setCpf("12345678900");
        customer.setEmail("p7gPQ@example.com");
        customer.setPhone("1234567890");
        customer.setCnpj("12345678900000");
        customer.setCompanyName("Company Name");
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());
        customer.setActive(true);
        customer.setCurrentFunds(BigDecimal.ZERO);
        customer.setCredit(BigDecimal.ZERO);
        customer.setPlan(plan);

        when(planService.findById(plan.getId())).thenReturn(plan);
        when(customerRepository.existsByCpf(anyString())).thenReturn(false);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        var result = customerService.create(customer);

        assertNotNull(result);
        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(customerCaptor.capture());

        var savedCustomer = customerCaptor.getValue();
        assertEquals(customer.getName(), savedCustomer.getName());
        assertEquals(customer.getEmail(), savedCustomer.getEmail());
    }

    @Test
    void testCreate_CustomerAlreadyExists() {
        var customer = new Customer();
        customer.setCpf("12345678900");
        var plan = new Plan();
        plan.setActive(true);
        customer.setPlan(plan);
        when(planService.findById(plan.getId())).thenReturn(plan);
        when(customerRepository.existsByCpf(anyString())).thenReturn(true);

        var thrown = assertThrows(SmsException.class, () -> customerService.create(customer));
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatus());
        assertEquals("Customer already exists!", thrown.getMessage());
    }

    @Test
    void testFindBalance() {
        var customerId = UUID.randomUUID();
        var customer = new Customer();
        customer.setName("John Doe");
        customer.setActive(true);
        customer.setCurrentFunds(BigDecimal.valueOf(100.00));
        customer.setCredit(BigDecimal.valueOf(50.00));
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        var balanceVO = customerService.findBalance(customerId);
        assertNotNull(balanceVO);
        assertEquals("John Doe", balanceVO.getCustomerName());
        assertEquals(BigDecimal.valueOf(100.00), balanceVO.getBalance().get("currentFunds"));
        assertEquals(BigDecimal.valueOf(50.00), balanceVO.getBalance().get("credit"));
    }

    @Test
    void testAddCredit() {
        var customerId = UUID.randomUUID();
        var customer = new Customer();
        customer.setCredit(BigDecimal.ZERO);
        customer.setActive(true);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        customerService.addCredit(customerId, BigDecimal.valueOf(50.00));
        assertEquals(BigDecimal.valueOf(50.00), customer.getCredit());
        verify(customerRepository).save(customer);
    }

    @Test
    void testAddFunds() {
        var customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setCurrentFunds(BigDecimal.ZERO);
        customer.setActive(true);
        var newLimit = BigDecimal.valueOf(100);

        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        customerService.addFunds(customer.getId(), newLimit);

        verify(customerRepository).findById(customer.getId());
        verify(customerRepository).save(customer);
    }

    @Test
    void testChangePlan() {
        var customer = new Customer();
        var plan = new Plan();
        plan.setId(UUID.randomUUID());
        plan.setActive(true);

        customer.setId(UUID.randomUUID());
        customer.setActive(true);

        when(planService.findById(plan.getId())).thenReturn(plan);
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        customerService.changePlan(customer.getId(), plan.getId());

        verify(customerRepository).findById(customer.getId());
        verify(planService).findById(plan.getId());
        verify(customerRepository).save(customer);
    }

    @Test
    void testDelete() {
        var customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setActive(true);

        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        customerService.delete(customer.getId());

        verify(customerRepository).findById(customer.getId());
        verify(customerRepository).save(customer);
    }

}

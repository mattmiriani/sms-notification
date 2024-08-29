package com.example.sms.service;

import com.example.sms.exception.SmsException;
import com.example.sms.entity.Customer;
import com.example.sms.objetoveiw.CustomerBalanceImplementation;
import com.example.sms.objetoveiw.CustomerBalanceVO;
import com.example.sms.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor(onConstructor_ = @__(@Autowired))
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PlanService planService;

    @Transactional(readOnly = true)
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Customer findById(UUID customerId) {
        return customerRepository.findById(customerId).filter(Customer::getActive).orElseThrow(
                () -> new SmsException(NOT_FOUND, "Customer not found!")
        );
    }

    @Transactional(readOnly = true)
    public CustomerBalanceVO findBalance(UUID customerId) {
        var customer = this.findById(customerId);

        return new CustomerBalanceImplementation(
                customer.getName(),
                customer.getCurrentFunds(),
                customer.getCredit()
        );
    }

    private Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Customer create(Customer customer) {
        var validatedPlan = planService.findById(customer.getPlan().getId());
        this.verifyCPF(customer.getCpf());

        customer.setPlan(validatedPlan);

        return this.save(new Customer(customer));
    }

    private void verifyCPF(String cpf) {
        if (customerRepository.existsByCpf(cpf)) {
            throw new SmsException(BAD_REQUEST, "Customer already exists!");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Customer update(UUID customerId, Customer customer) {
        var customerToUpdate = this.findById(customerId);

        customerToUpdate.mergeForUpdate(customer);

        return this.save(customerToUpdate);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addCredit(UUID customerId, BigDecimal credit) {
        var customerToUpdate = this.findById(customerId);

        customerToUpdate.addCredit(credit);

        this.save(customerToUpdate);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addFunds(UUID customerId, BigDecimal newLimit) {
        var customerToUpdate = this.findById(customerId);

        customerToUpdate.addFunds(newLimit);

        this.save(customerToUpdate);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void changePlan(UUID customerId, UUID planId) {
        var customerToUpdate = this.findById(customerId);
        var validatedPlan = planService.findById(planId);

        customerToUpdate.changePlan(validatedPlan);

        this.save(customerToUpdate);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(UUID customerId) {
        var customerToDelete = this.findById(customerId);

        customerToDelete.setUpdatedAt(LocalDateTime.now());
        customerToDelete.setActive(false);

        this.save(customerToDelete);
    }
}

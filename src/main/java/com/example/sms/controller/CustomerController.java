package com.example.sms.controller;

import com.example.sms.dto.PageableDTO;
import com.example.sms.entity.Customer;
import com.example.sms.objetoveiw.CustomerBalanceVO;
import com.example.sms.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@AllArgsConstructor(onConstructor_ = @__(@Autowired))
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<Page<Customer>> findAll(@RequestBody PageableDTO pageableDTO) {

        try {
            return ResponseEntity.ok(customerService.findAll(pageableDTO.getPageable()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable("id") UUID customerId) {
        try {
            return ResponseEntity.ok(customerService.findById(customerId));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/balance/{id}")
    public ResponseEntity<CustomerBalanceVO> findBalance(@PathVariable("id") UUID customerId) {
        try {
            return ResponseEntity.ok(customerService.findBalance(customerId));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        try {
            return ResponseEntity.status(CREATED).body(customerService.create(customer));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable("id") UUID customerId,
                                           @RequestBody Customer customer) {
        try {
            return ResponseEntity.ok(customerService.update(customerId, customer));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}/credit")
    public ResponseEntity<Void> addCredit(@PathVariable("id") UUID customerId,
                                          @RequestParam("credit") BigDecimal credit) {
        try {
            customerService.addCredit(customerId, credit);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}/change-limit")
    public ResponseEntity<Void> changeLimit(@PathVariable("id") UUID customerId,
                                            @RequestParam("limit") BigDecimal limit) {
        try {
            customerService.changeLimit(customerId, limit);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}/change-plan")
    public ResponseEntity<Void> changePlan(@PathVariable("id") UUID customerId,
                                           @RequestParam("plan") UUID planId) {
        try {
        customerService.changePlan(customerId, planId);
        return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID customerId) {
        try {
            customerService.delete(customerId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

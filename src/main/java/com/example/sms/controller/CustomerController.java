package com.example.sms.controller;

import com.example.sms.dto.CustomerDTO;
import com.example.sms.dto.PageableDTO;
import com.example.sms.mapper.CustomerMapper;
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
    private final CustomerMapper customerMapper;

    @GetMapping
    public ResponseEntity<Page<CustomerDTO>> findAll(PageableDTO pageableDTO) {
        try {
            var response = customerService.findAll(pageableDTO.getPageable());

            return ResponseEntity.ok(response.map(customerMapper::customerToCustomerDTO));
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable("id") UUID customerId) {
        try {
            var response = customerService.findById(customerId);

            return ResponseEntity.ok(customerMapper.customerToCustomerDTO(response));
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/balance/{id}")
    public ResponseEntity<CustomerBalanceVO> findBalance(@PathVariable("id") UUID customerId) {
        try {
            return ResponseEntity.ok(customerService.findBalance(customerId));
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) {
        try {
            var customer = customerMapper.customerDTOToCustomer(customerDTO);
            var response = customerService.create(customer);

            return ResponseEntity.status(CREATED).body(customerMapper.customerToCustomerDTO(response));
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> update(@PathVariable("id") UUID customerId,
                                              @RequestBody CustomerDTO customerDTO) {
        try {
            var customer = customerMapper.customerDTOToCustomerUpdate(customerDTO);
            var response = customerService.update(customerId, customer);

            return ResponseEntity.ok(customerMapper.customerToCustomerDTOUpdate(response));
        } catch (Exception e) {
            e.printStackTrace();

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
            e.printStackTrace();

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
            e.printStackTrace();

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
            e.printStackTrace();

            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID customerId) {
        try {
            customerService.delete(customerId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.notFound().build();
        }
    }
}

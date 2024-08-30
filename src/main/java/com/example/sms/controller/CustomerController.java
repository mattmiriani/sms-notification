package com.example.sms.controller;

import com.example.sms.exception.SmsException;
import com.example.sms.dto.CustomerDTO;
import com.example.sms.dto.PageableDTO;
import com.example.sms.mapper.CustomerMapper;
import com.example.sms.objetoveiw.CustomerBalanceVO;
import com.example.sms.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor(onConstructor_ = @__(@Autowired))
@RestController
@RequestMapping("/customers")
public class CustomerController extends ControllerDefault {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping
    public ResponseEntity<Page<CustomerDTO>> findAll(PageableDTO pageableDTO) {
        try {
            var response = customerService.findAll(pageableDTO.getPageable());

            return ResponseEntity.ok(response.map(customerMapper::customerToCustomerDTO));
        } catch (SmsException e) {

            throw new SmsException(e.getStatus(), e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable("id") UUID customerId) {
        try {
            var response = customerService.findById(customerId);

            return ResponseEntity.ok(customerMapper.customerToCustomerDTO(response));
        } catch (SmsException e) {

            throw new SmsException(e.getStatus(), e.getMessage());
        }
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<CustomerBalanceVO> findBalance(@PathVariable("id") UUID customerId) {
        try {
            return ResponseEntity.ok(customerService.findBalance(customerId));
        } catch (SmsException e) {

            throw new SmsException(e.getStatus(), e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) {
        try {
            var customer = customerMapper.customerDTOToCustomer(customerDTO);
            var response = customerService.create(customer);

            return ResponseEntity.status(HttpStatus.CREATED).body(customerMapper.customerToCustomerDTO(response));
        } catch (SmsException e) {

            throw new SmsException(e.getStatus(), e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> update(@PathVariable("id") UUID customerId,
                                              @RequestBody CustomerDTO customerDTO) {
        try {
            var customer = customerMapper.customerDTOToCustomerUpdate(customerDTO);
            var response = customerService.update(customerId, customer);

            return ResponseEntity.ok(customerMapper.customerToCustomerDTOUpdate(response));
        } catch (SmsException e) {

            throw new SmsException(e.getStatus(), e.getMessage());
        }
    }

    @PutMapping("/{id}/credit")
    public ResponseEntity<Void> addCredit(@PathVariable("id") UUID customerId,
                                          @RequestParam("credit") BigDecimal credit) {
        try {
            customerService.addCredit(customerId, credit);
            return ResponseEntity.ok().build();
        } catch (SmsException e) {

            throw new SmsException(e.getStatus(), e.getMessage());
        }
    }

    @PutMapping("/{id}/funds")
    public ResponseEntity<Void> addFunds(@PathVariable("id") UUID customerId,
                                         @RequestParam("funds") BigDecimal funds) {
        try {
            customerService.addFunds(customerId, funds);
            return ResponseEntity.ok().build();
        } catch (SmsException e) {

            throw new SmsException(e.getStatus(), e.getMessage());
        }
    }

    @PutMapping("/{id}/plan")
    public ResponseEntity<Void> changePlan(@PathVariable("id") UUID customerId,
                                           @RequestParam("plan") UUID planId) {
        try {
            customerService.changePlan(customerId, planId);
            return ResponseEntity.ok().build();
        } catch (SmsException e) {

            throw new SmsException(e.getStatus(), e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID customerId) {
        try {
            customerService.delete(customerId);
            return ResponseEntity.noContent().build();
        } catch (SmsException e) {

            throw new SmsException(e.getStatus(), e.getMessage());
        }
    }
}

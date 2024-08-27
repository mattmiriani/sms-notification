package com.example.sms.service;

import com.example.sms.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor(onConstructor_ = @__(@Autowired))
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    //TODO: criar um crud de customers
}

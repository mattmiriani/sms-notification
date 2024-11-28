package com.example.sms.mapper;

import com.example.sms.dto.CustomerDTO;
import com.example.sms.dto.PlanDTO;
import com.example.sms.entity.Customer;
import com.example.sms.entity.Plan;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-27T23:02:54-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO customerToCustomerDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setPlan( planToPlanDTO( customer.getPlan() ) );
        customerDTO.setActive( customer.getActive() );
        customerDTO.setCnpj( customer.getCnpj() );
        customerDTO.setCompanyName( customer.getCompanyName() );
        customerDTO.setCpf( customer.getCpf() );
        customerDTO.setCreatedAt( customer.getCreatedAt() );
        customerDTO.setCredit( customer.getCredit() );
        customerDTO.setCurrentFunds( customer.getCurrentFunds() );
        customerDTO.setEmail( customer.getEmail() );
        customerDTO.setId( customer.getId() );
        customerDTO.setName( customer.getName() );
        customerDTO.setPhone( customer.getPhone() );
        customerDTO.setUpdatedAt( customer.getUpdatedAt() );

        return customerDTO;
    }

    @Override
    public Customer customerDTOToCustomer(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setPlan( planDTOToPlan( customerDTO.getPlan() ) );
        customer.setActive( customerDTO.getActive() );
        customer.setCnpj( customerDTO.getCnpj() );
        customer.setCompanyName( customerDTO.getCompanyName() );
        customer.setCpf( customerDTO.getCpf() );
        customer.setCreatedAt( customerDTO.getCreatedAt() );
        customer.setCredit( customerDTO.getCredit() );
        customer.setCurrentFunds( customerDTO.getCurrentFunds() );
        customer.setEmail( customerDTO.getEmail() );
        customer.setId( customerDTO.getId() );
        customer.setName( customerDTO.getName() );
        customer.setPhone( customerDTO.getPhone() );
        customer.setUpdatedAt( customerDTO.getUpdatedAt() );

        return customer;
    }

    @Override
    public CustomerDTO customerToCustomerDTOUpdate(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId( customer.getId() );
        customerDTO.setName( customer.getName() );
        customerDTO.setEmail( customer.getEmail() );
        customerDTO.setPhone( customer.getPhone() );
        customerDTO.setCompanyName( customer.getCompanyName() );
        customerDTO.setActive( customer.getActive() );
        customerDTO.setCnpj( customer.getCnpj() );
        customerDTO.setCpf( customer.getCpf() );
        customerDTO.setCreatedAt( customer.getCreatedAt() );
        customerDTO.setCredit( customer.getCredit() );
        customerDTO.setCurrentFunds( customer.getCurrentFunds() );
        customerDTO.setUpdatedAt( customer.getUpdatedAt() );

        return customerDTO;
    }

    @Override
    public Customer customerDTOToCustomerUpdate(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setName( customerDTO.getName() );
        customer.setEmail( customerDTO.getEmail() );
        customer.setPhone( customerDTO.getPhone() );
        customer.setCompanyName( customerDTO.getCompanyName() );
        customer.setActive( customerDTO.getActive() );
        customer.setCnpj( customerDTO.getCnpj() );
        customer.setCpf( customerDTO.getCpf() );
        customer.setCreatedAt( customerDTO.getCreatedAt() );
        customer.setCredit( customerDTO.getCredit() );
        customer.setCurrentFunds( customerDTO.getCurrentFunds() );
        customer.setId( customerDTO.getId() );
        customer.setUpdatedAt( customerDTO.getUpdatedAt() );

        return customer;
    }

    protected PlanDTO planToPlanDTO(Plan plan) {
        if ( plan == null ) {
            return null;
        }

        PlanDTO planDTO = new PlanDTO();

        planDTO.setActive( plan.getActive() );
        planDTO.setCreatedAt( plan.getCreatedAt() );
        planDTO.setCredit( plan.getCredit() );
        planDTO.setId( plan.getId() );
        planDTO.setMessageAmount( plan.getMessageAmount() );
        planDTO.setType( plan.getType() );
        planDTO.setUpdatedAt( plan.getUpdatedAt() );

        return planDTO;
    }

    protected Plan planDTOToPlan(PlanDTO planDTO) {
        if ( planDTO == null ) {
            return null;
        }

        Plan plan = new Plan();

        plan.setActive( planDTO.getActive() );
        plan.setCreatedAt( planDTO.getCreatedAt() );
        plan.setCredit( planDTO.getCredit() );
        plan.setId( planDTO.getId() );
        plan.setMessageAmount( planDTO.getMessageAmount() );
        plan.setType( planDTO.getType() );
        plan.setUpdatedAt( planDTO.getUpdatedAt() );

        return plan;
    }
}

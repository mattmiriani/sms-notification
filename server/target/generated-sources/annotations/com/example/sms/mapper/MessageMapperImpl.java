package com.example.sms.mapper;

import com.example.sms.dto.CustomerDTO;
import com.example.sms.dto.MessageDTO;
import com.example.sms.dto.PlanDTO;
import com.example.sms.entity.Customer;
import com.example.sms.entity.Message;
import com.example.sms.entity.Plan;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-27T23:02:55-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class MessageMapperImpl implements MessageMapper {

    @Override
    public MessageDTO messageToMessageDTO(Message message) {
        if ( message == null ) {
            return null;
        }

        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setCustomer( customerToCustomerDTO( message.getCustomer() ) );
        messageDTO.setCreatedAt( message.getCreatedAt() );
        messageDTO.setId( message.getId() );
        messageDTO.setPhoneNumber( message.getPhoneNumber() );
        messageDTO.setText( message.getText() );
        messageDTO.setWhatsApp( message.isWhatsApp() );

        return messageDTO;
    }

    @Override
    public Message messageDTOToMessage(MessageDTO messageDTO) {
        if ( messageDTO == null ) {
            return null;
        }

        Message message = new Message();

        message.setCustomer( customerDTOToCustomer( messageDTO.getCustomer() ) );
        message.setCreatedAt( messageDTO.getCreatedAt() );
        message.setId( messageDTO.getId() );
        message.setPhoneNumber( messageDTO.getPhoneNumber() );
        message.setText( messageDTO.getText() );
        message.setWhatsApp( messageDTO.isWhatsApp() );

        return message;
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

    protected CustomerDTO customerToCustomerDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

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
        customerDTO.setPlan( planToPlanDTO( customer.getPlan() ) );
        customerDTO.setUpdatedAt( customer.getUpdatedAt() );

        return customerDTO;
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

    protected Customer customerDTOToCustomer(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        Customer customer = new Customer();

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
        customer.setPlan( planDTOToPlan( customerDTO.getPlan() ) );
        customer.setUpdatedAt( customerDTO.getUpdatedAt() );

        return customer;
    }
}

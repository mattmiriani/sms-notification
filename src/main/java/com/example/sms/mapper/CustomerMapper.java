package com.example.sms.mapper;

import com.example.sms.dto.CustomerDTO;
import com.example.sms.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "plan", target = "plan")
    CustomerDTO customerToCustomerDTO(Customer customer);

    @Mapping(source = "plan", target = "plan")
    Customer customerDTOToCustomer(CustomerDTO customerDTO);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "phone", target = "phone"),
            @Mapping(source = "companyName", target = "companyName"),
            @Mapping(target = "plan", ignore = true)
    })
    CustomerDTO customerToCustomerDTOUpdate(Customer customer);

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "phone", target = "phone"),
            @Mapping(source = "companyName", target = "companyName"),
            @Mapping(target = "plan", ignore = true)
    })
    Customer customerDTOToCustomerUpdate(CustomerDTO customerDTO);

}

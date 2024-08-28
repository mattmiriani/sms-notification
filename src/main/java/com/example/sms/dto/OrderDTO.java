package com.example.sms.dto;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class OrderDTO {

    private String field;
    private Sort.Direction sortDir = Sort.Direction.ASC;

    public Sort.Order getOrder() {
        return new Sort.Order(sortDir, field);
    }
}
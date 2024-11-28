package com.example.sms.dto;

import com.example.sms.entity.Plan;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CustomerDTO {

    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String cpf;
    private String cnpj;
    private String companyName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean active;
    private BigDecimal currentFunds;
    private BigDecimal credit;
    private PlanDTO plan;

}

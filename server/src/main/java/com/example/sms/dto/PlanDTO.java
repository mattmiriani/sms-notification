package com.example.sms.dto;

import com.example.sms.enumeration.PlanType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class PlanDTO {

    private UUID id;
    private PlanType type;
    private BigDecimal messageAmount;
    private BigDecimal credit;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

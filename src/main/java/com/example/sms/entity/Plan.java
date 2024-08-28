package com.example.sms.entity;

import com.example.sms.enumeration.PlanType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity(name = "plan")
public class Plan implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private PlanType type;

    @Column(name = "limit", nullable = false)
    private BigDecimal smsCost;

    @Column(name = "credit_limit", nullable = false)
    private BigDecimal creditLimit;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Plan(Plan plan) {
        this.id = UUID.randomUUID();
        this.type = plan.getType();
        this.smsCost = plan.getSmsCost();
        this.active = Boolean.TRUE;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void mergeForUpdate(Plan plan) {
        this.type = plan.getType();
        this.smsCost = plan.getSmsCost();
        this.updatedAt = LocalDateTime.now();
    }
}

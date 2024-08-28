package com.example.sms.entity;

import com.example.sms.config.exception.SmsException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity(name = "customer")
public class Customer implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false, length = 11)
    private String phone;

    @CPF
    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private String cpf;

    @CNPJ
    @Column(name = "cnpj", nullable = false, unique = true, length = 14)
    private String cnpj;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "credit_limit", nullable = false)
    private BigDecimal creditLimit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    public Customer(Customer customer) {
        this.id = UUID.randomUUID();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
        this.cpf = customer.getCpf();
        this.cnpj = customer.getCnpj();
        this.companyName = customer.getCompanyName();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.active = Boolean.TRUE;
    }

    public void addCredit(BigDecimal credit) {
        this.balance = this.balance.add(credit);
    }

    public BigDecimal checkBalance() {
        return this.balance.subtract(this.creditLimit);
    }

    public void changeLimit(BigDecimal newLimit) {
        if (newLimit.compareTo(BigDecimal.ZERO) < 0) {
            throw new SmsException(HttpStatus.BAD_REQUEST, "Limit must be greater than zero");
        }

        this.creditLimit = newLimit;
    }

    public void changePlan(Plan plan) {
        if (!plan.getActive()) {
            throw new SmsException(HttpStatus.BAD_REQUEST, "Plan not active");
        }

        this.plan = plan;
    }

    public void mergeForUpdate(Customer customer) {
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
        this.cpf = customer.getCpf();
        this.cnpj = customer.getCnpj();
        this.companyName = customer.getCompanyName();
        this.updatedAt = LocalDateTime.now();
    }
}

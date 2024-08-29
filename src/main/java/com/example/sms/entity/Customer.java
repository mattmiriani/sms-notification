package com.example.sms.entity;

import com.example.sms.exception.SmsException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false, length = 11)
    private String phone;

    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private String cpf;

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

    @Column(name = "current_funds", nullable = false)
    private BigDecimal currentFunds;

    @Column(name = "credit", nullable = false)
    private BigDecimal credit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    public Customer(Customer customer) {
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
        this.cpf = customer.getCpf();
        this.cnpj = customer.getCnpj();
        this.companyName = customer.getCompanyName();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.active = Boolean.TRUE;
        this.currentFunds = customer.getCurrentFunds();

        this.changePlan(customer.getPlan());
    }

    public void addCredit(BigDecimal credit) {
        this.credit = this.credit.add(credit);
    }

    public void addFunds(BigDecimal funds) {
        this.credit = this.currentFunds.add(funds);
    }

    public void changePlan(Plan plan) {
        if (!plan.getActive()) {
            throw new SmsException(HttpStatus.NOT_FOUND, "Plan not found!");
        }

        this.credit = plan.getCredit();
        this.plan = plan;
    }

    public void mergeForUpdate(Customer customer) {
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
        this.companyName = customer.getCompanyName();
        this.updatedAt = LocalDateTime.now();
    }
}

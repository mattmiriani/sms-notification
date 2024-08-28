package com.example.sms.objetoveiw;

import lombok.Setter;

import java.math.BigDecimal;

@Setter
public class CustomerBalanceImplementation implements CustomerBalanceVO {

    private String customerName;
    private BigDecimal balance;

    public CustomerBalanceImplementation(String customerName, BigDecimal balance) {
        this.customerName = customerName;
        this.balance = balance;
    }

    @Override
    public String getCustomerName() {
        return customerName;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

}

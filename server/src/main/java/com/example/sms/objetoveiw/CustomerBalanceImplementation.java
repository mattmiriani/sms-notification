package com.example.sms.objetoveiw;

import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Setter
public class CustomerBalanceImplementation implements CustomerBalanceVO {

    private String customerName;
    private Map<String, BigDecimal> balance;

    public CustomerBalanceImplementation(String customerName, BigDecimal currentFunds, BigDecimal credit) {
        this.customerName = customerName;
        this.balance = Map.of("currentFunds", currentFunds, "credit", credit);
    }

    @Override
    public String getCustomerName() {
        return customerName;
    }

    @Override
    public Map<String, BigDecimal> getBalance() {
        return balance;
    }
}

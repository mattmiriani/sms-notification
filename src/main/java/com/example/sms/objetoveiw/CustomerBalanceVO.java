package com.example.sms.objetoveiw;

import java.math.BigDecimal;
import java.util.Map;

public interface CustomerBalanceVO {

    String getCustomerName();
    Map<String, BigDecimal> getBalance();

}

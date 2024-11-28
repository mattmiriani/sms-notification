package com.example.sms.service;

import com.example.sms.exception.SmsException;
import com.example.sms.entity.Message;
import com.example.sms.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.example.sms.enumeration.PlanType.Balance;
import static com.example.sms.enumeration.PlanType.Credit;

@AllArgsConstructor(onConstructor_ = @__(@Autowired))
@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final CustomerService customerService;
    private final PlanService planService;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Message send(Message message) {
        var validatedCustomer = customerService.findById(message.getCustomer().getId());

        message.setCustomer(validatedCustomer);
        this.subtractCreditOrBalance(message);

        return messageRepository.save(new Message(message));
    }

    private void subtractCreditOrBalance(Message message) {
        var customer = message.getCustomer();
        var plan = planService.findById(customer.getPlan().getId());

        if (plan.getType().equals(Balance) && customer.getCurrentFunds().compareTo(plan.getMessageAmount()) < 0) {
            throw new SmsException(HttpStatus.BAD_REQUEST, "Not enough balance!");
        }

        if (plan.getType().equals(Credit) && customer.getCredit().compareTo(plan.getMessageAmount()) < 0) {
            throw new SmsException(HttpStatus.BAD_REQUEST, "Not enough credit!");
        }

        switch (plan.getType()) {
            case Balance:
                customer.setCurrentFunds(customer.getCurrentFunds().subtract(plan.getMessageAmount()));
                break;
            case Credit:
                customer.setCredit(customer.getCredit().subtract(plan.getMessageAmount()));
                break;
        }

    }
}

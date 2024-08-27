package com.example.sms.controller;

import com.example.sms.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;

@AllArgsConstructor(onConstructor_ = @__(@Autowired))
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/test")
    public ResponseEntity<String> getTest() {
        var hostName = getHostName();
        var osBean = ManagementFactory.getOperatingSystemMXBean();
        var osName = osBean.getName();
        var osVersion = osBean.getVersion();
        var availableProcessors = osBean.getAvailableProcessors();
        var freeMemory = Runtime.getRuntime().freeMemory();
        var totalMemory = Runtime.getRuntime().totalMemory();

        return ResponseEntity.ok().body(
                String.format(
                        "Hello World!\nHost: %s\nOS: %s %s\nAvailable Processors: %d\nFree Memory: %d MB\nTotal Memory: %d MB",
                        hostName, osName, osVersion, availableProcessors, freeMemory / (1024 * 1024), totalMemory / (1024 * 1024)
                )
        );
    }

    private String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "Unknown Host";
        }
    }
}

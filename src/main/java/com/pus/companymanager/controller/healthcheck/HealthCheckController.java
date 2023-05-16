package com.pus.companymanager.controller.healthcheck;

import com.pus.companymanager.service.healthcheck.HealthCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HealthCheckController {

    private final HealthCheckService healthCheckService;

    @GetMapping("/health")
    public String checkHealth() {
       return healthCheckService.healthCheck();
    }
}

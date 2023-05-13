package com.pus.companymanager.service.healthcheck;

 import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HealthCheckService {

    private final PostgresHealthCheckService postgresHealthCheckService;
    private final MongoDbHealthCheckService mongoDbHealthCheckService;
    private final RabbitMqHealthCheckService rabbitMqHealthCheckService;
    private final RedisHealthCheckService redisHealthCheckService;

    @SneakyThrows
    public String healthCheck() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> healthStatus = new HashMap<>();

        healthStatus.put("redis", redisHealthCheckService.checkHealth());
        healthStatus.put("postgres", postgresHealthCheckService.checkHealth());
        healthStatus.put("mongo", mongoDbHealthCheckService.checkHealth());
        healthStatus.put("rabbit", rabbitMqHealthCheckService.checkHealth());

        return mapper.writeValueAsString(healthStatus);
    }
}

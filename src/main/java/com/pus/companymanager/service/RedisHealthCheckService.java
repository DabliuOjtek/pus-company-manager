package com.pus.companymanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.RedisConnectionCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisHealthCheckService {

    private final RedisTemplate<String, String> redisTemplate;

    public String checkHealth() {
        System.out.println(">> Redis health check START");

        String pingResponse;
        try {
            pingResponse = redisTemplate.execute(RedisConnectionCommands::ping);
        } catch (Exception exception) {
            return StatusUtil.failed();
        }

        System.out.println("<< Redis health check END");

        return StatusUtil.getStatus("PONG".equals(pingResponse));
    }


}


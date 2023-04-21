package com.pus.companymanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostgresHealthCheckService {

    private final JdbcTemplate jdbcTemplate;

    public String checkHealth() {
        System.out.println(">> Postgres health check START");

        Integer result;
        try {
            result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        } catch (DataAccessException exception){
            return StatusUtil.failed();
        }

        System.out.println("<< Postgres health check END");

        return StatusUtil.getStatus(result != null && result == 1);
    }

}

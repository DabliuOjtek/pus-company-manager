package com.pus.companymanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MongoDbHealthCheckService {

    @Value("${spring.data.mongodb.port}")
    private String mongoPort;

    private final RestTemplate restTemplate;

    public String checkHealth() {
        System.out.println(">> Mongo health check START");

        String url = "http://mongo:" + mongoPort;
        HttpStatusCode httpStatus;

        try {
            httpStatus = this.restTemplate.getForEntity(url, String.class).getStatusCode();
        } catch (RestClientException exception) {
            return StatusUtil.failed();
        }

        System.out.println("<< Mongo health check END");

        return StatusUtil.getStatus(httpStatus.is2xxSuccessful());
    }

}

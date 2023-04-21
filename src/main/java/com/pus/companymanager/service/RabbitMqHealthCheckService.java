package com.pus.companymanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RabbitMqHealthCheckService {

    @Value("${spring.rabbitmq.port}")
    private String rabbitPort;

    @Value("${spring.rabbitmq.username}")
    private String rabbitUsername;

    @Value("${spring.rabbitmq.password}")
    private String rabbitPassword;
    private static final String ENDPOINT = "/api/healthchecks/node";

    private final RestTemplate restTemplate;

    public String checkHealth() {
        System.out.println(">> RabbitMq health check START");

        String url = "http://rabbitmq:" + rabbitPort + ENDPOINT;

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(rabbitUsername, rabbitPassword);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        HttpStatusCode httpStatus;

        try {
            httpStatus = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class).getStatusCode();
        } catch (RestClientException exception) {
            return StatusUtil.failed();
        }

        System.out.println("<< RabbitMq health check END");

        return StatusUtil.getStatus(httpStatus.is2xxSuccessful());
    }

}


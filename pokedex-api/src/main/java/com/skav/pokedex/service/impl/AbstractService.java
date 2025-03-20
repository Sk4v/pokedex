package com.skav.pokedex.service.impl;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

public abstract class AbstractService {

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> mapResponseFromGetRequest(URI apiUrl) {
        ResponseEntity<Map> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, null, Map.class);
        return restTemplate.getForObject(apiUrl, Map.class);
    }

    public Map<String, Object> mapResponseFromPostRequest(URI apiUrl, Object requestBody) {
        return restTemplate.postForObject(apiUrl, requestBody, Map.class);
    }


}

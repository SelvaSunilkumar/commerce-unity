package com.github.SelvaSunilkumar.Commerce.Unity.utility;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Service
public class ServiceBridge {
    @Autowired
    private RestTemplate restTemplate;

    public <T>ResponseEntity<T> get(String url, Class<T> responseType) {
        return get(url, responseType, null);
    }

    public <T> ResponseEntity<T> get(String url, Class<T> responseType, Map<String, String> headers) {
        try {
            HttpHeaders httpHeaders = createHeaders(headers);
            HttpEntity<T> entity = new HttpEntity<>(httpHeaders);

            log.info("Making GET request to {}", url);
            return restTemplate.exchange(url, HttpMethod.GET, entity, responseType);
        } catch (RestClientException e) {
            log.error("Error making GET request to {}", url, e);
            throw e;
        }
    }

    public <T> ResponseEntity<T> post(String url, Object requestBody, Class<T> responseType) {
        return post(url, requestBody, responseType, null);
    }

    public <T> ResponseEntity<T> post(String url, Object requestBody, Class<T> responseType, Map<String, String> headers) {
        try {
            HttpHeaders httpHeaders = createHeaders(headers);
            HttpEntity<Object> entity = new HttpEntity<>(requestBody, httpHeaders);

            log.info("Making POST request to {}", url);
            return restTemplate.exchange(url, HttpMethod.POST, entity, responseType);
        } catch (RestClientException e) {
            log.error("Error making POST request to {}", url, e);
            throw e;
        }
    }

    public  <T> ResponseEntity<T> put(String url, Object requestBody, Class<T> responseType) {
        return put(url, requestBody, responseType, null);
    }

    public <T> ResponseEntity<T> put(String url, Object requestBody, Class<T> responseType, Map<String, String> headers) {
        try {
            HttpHeaders httpHeaders = createHeaders(headers);
            HttpEntity<Object> entity = new HttpEntity<>(requestBody, httpHeaders);

            log.info("Making PUT request to {}", url);
            return restTemplate.exchange(url, HttpMethod.PUT, entity, responseType);
        } catch (RestClientException e) {
            log.error("Error making PUT request to {}", url, e);
            throw e;
        }
    }

    public <T> ResponseEntity<T> delete(String url, Class<T> responseType) {
        return delete(url, responseType, null);
    }

    public <T> ResponseEntity<T> delete(String url, Class<T> responseType, Map<String, String> headers) {
        try {
            HttpHeaders httpHeaders = createHeaders(headers);
            HttpEntity<Object> entity = new HttpEntity<>(httpHeaders);

            log.info("Making DELETE request to {}", url);
            return restTemplate.exchange(url, HttpMethod.DELETE, entity, responseType);
        } catch (RestClientException e) {
            log.error("Error making DELETE request to {}", url, e);
            throw e;
        }
    }

    private HttpHeaders createHeaders(Map<String, String> headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        if (headers != null) {
            headers.forEach(httpHeaders::set);
        }

        return httpHeaders;
    }
}

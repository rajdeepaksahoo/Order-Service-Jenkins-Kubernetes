package com.sb.docker.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/product/{productId}")
    public ResponseEntity<Boolean> placeOrder(@PathVariable Long productId) {

        ResponseEntity<Boolean> response = restTemplate.getForEntity(
                "http://PRODUCT-SERVICE/ifProductAvailable/" + productId,
                Boolean.class
        );

        Boolean available = response.getBody();

        return ResponseEntity.ok(available);
    }

    @GetMapping
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Working Fine", HttpStatus.OK);
    }
}

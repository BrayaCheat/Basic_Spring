package com._5.basic.controller;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("/rate-limit")
public class RateLimitController {

    private final Bucket bucket;

    public RateLimitController() {
        Refill refill = Refill.intervally(3, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(3, refill);
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @GetMapping
    public ResponseEntity<String> demo() {
        if(bucket.tryConsume(1)){
            return ResponseEntity.status(200).body("Request accepted.");
        }else{
            return ResponseEntity.status(429).body("Too many request.");
        }
    }
}

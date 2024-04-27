package com.example.springretryissue.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HomeService {

    @Retryable(retryFor = {RuntimeException.class},
            maxAttemptsExpression = "${retry.max-attempts}",
            backoff = @Backoff(delayExpression = "${retry.delay}",
                    multiplierExpression = "${retry.multiplier}",
                    randomExpression = "${retry.random}"))
    public void home() {
        System.out.println(LocalDateTime.now().getSecond() + " Executing the service");

        throw new RuntimeException("Exception while executing");
    }
}

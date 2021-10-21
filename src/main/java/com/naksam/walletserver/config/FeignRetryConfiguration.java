package com.naksam.walletserver.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignRetryConfiguration {
    @Bean
    public Retryer retryer() {
        return new Retryer.Default(1000, 2000, 3);
//        return Retryer.NEVER_RETRY;
    }
}
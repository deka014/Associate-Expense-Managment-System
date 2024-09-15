package com.adp.ExpenseService.config;

import com.adp.ExpenseService.security.JwtTokenFeignClientInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor(JwtTokenFeignClientInterceptor jwtTokenFeignClientInterceptor) {
        return jwtTokenFeignClientInterceptor;
    }
}

package com.adp.ExpenseService.security;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenFeignClientInterceptor implements RequestInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtTokenFeignClientInterceptor(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void apply(RequestTemplate template) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        if (authentication != null) {
            String token = jwtTokenProvider.generate(authentication);
            template.header("Authorization", "Bearer " + token);
        }
    }
}
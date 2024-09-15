package com.adp.ExpenseService.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    CustomUserDetails userDetailsService;
    @Autowired
    JwtTokenProvider tokenProvider;
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    public JwtAuthenticationFilter() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            getJwtFromRequest(request)
                    .flatMap(tokenProvider::validateTokenAndGetJws)
                    .ifPresent(jws -> {
                        String username = jws.getBody().getSubject();
                        System.out.println(username);
                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        System.out.println(SecurityContextHolder.getContext());
                        System.out.println("here");
                    });
        } catch (Exception e) {
            log.error("Cannot set user authentication", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Invalid Request");
        }
        chain.doFilter(request, response);
    }

    private Optional<String> getJwtFromRequest(HttpServletRequest request) {
        String tokenHeader = request.getHeader(TOKEN_HEADER);
        if (StringUtils.hasText(tokenHeader) && tokenHeader.startsWith(TOKEN_PREFIX)) {
            return Optional.of(tokenHeader.replace(TOKEN_PREFIX, ""));
        }
        return Optional.empty();
    }

}
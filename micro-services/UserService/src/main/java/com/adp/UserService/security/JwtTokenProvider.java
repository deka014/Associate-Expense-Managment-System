package com.adp.UserService.security;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
@Slf4j
public class JwtTokenProvider {
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";



    public String generate(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority).toList();

        return Jwts.builder()
                .claim("role",roles)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 10000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Optional<Jws<Claims>> validateTokenAndGetJws(String token) {
        try {
            byte[] signingKey = Decoders.BASE64.decode(SECRET);

            Jws<Claims> jws = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(signingKey))
                    .build()
                    .parseClaimsJws(token);


            return Optional.of(jws);
        } catch (ExpiredJwtException exception) {
            log.error("Request to parse expired JWT : {} failed : {}", token, exception.getMessage());
        } catch (UnsupportedJwtException exception) {
            log.error("Request to parse unsupported JWT : {} failed : {}", token, exception.getMessage());
        } catch (MalformedJwtException exception) {
            log.error("Request to parse invalid JWT : {} failed : {}", token, exception.getMessage());
        } catch (SignatureException exception) {
            log.error("Request to parse JWT with invalid signature : {} failed : {}", token, exception.getMessage());
        } catch (IllegalArgumentException exception) {
            log.error("Request to parse empty or null JWT : {} failed : {}", token, exception.getMessage());
        }
        return Optional.empty();
    }

}
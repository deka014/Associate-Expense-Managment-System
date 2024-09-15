package com.adp.ExpenseService.config;

import com.adp.ExpenseService.security.JwtAuthenticationEntryPoint;
import com.adp.ExpenseService.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class AuthConfig {

	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private JwtAuthenticationFilter authenticationFilter;


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(requests->{
							requests.requestMatchers("/swagger/*").permitAll()
									.anyRequest().authenticated();
						}
				)
				.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}



	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}

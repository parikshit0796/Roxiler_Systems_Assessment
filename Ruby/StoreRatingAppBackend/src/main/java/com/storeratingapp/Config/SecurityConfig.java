package com.storeratingapp.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(AbstractHttpConfigurer::disable) 
        .authorizeHttpRequests(authorizeRequests -> authorizeRequests
				.requestMatchers("/**", "/swagger*/**","/v*/api-docs/**").permitAll()
				.requestMatchers(HttpMethod.OPTIONS).permitAll());
            

        return http.build();
    }

}

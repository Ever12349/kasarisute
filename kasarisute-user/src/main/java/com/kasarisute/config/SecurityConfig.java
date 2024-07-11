package com.kasarisute.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .httpBasic((httpBasicCongig)->{
                httpBasicCongig.disable();
            })
            .formLogin((formLoginConfig) -> {formLoginConfig.disable();})
            .csrf((csrfConfig) -> { csrfConfig.disable();})
            .logout((logoutConfig) -> {logoutConfig.disable();})
            .sessionManagement((session) -> {
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            })
            .authorizeHttpRequests((authorizeHttpRequests) -> {
                authorizeHttpRequests
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    ;
            });
		// @formatter:on
        return http.build();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> {
            web.ignoring().requestMatchers("/user/login", "/user/signin");
        };
    }
}

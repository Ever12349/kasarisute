package com.kasarisute.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

import com.kasarisute.security.JwtTokenOncePerRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] URL_WHITE_URL = { "/user/login", "/user/signin" };

    @Autowired
    @Qualifier("jwtAuthenticationEntryPoint")
    AuthenticationEntryPoint jwtAuthenticationEntryPoint;

    // @Autowired
    // AccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public JwtTokenOncePerRequestFilter authenticationJwtTokenFilter() {
        return new JwtTokenOncePerRequestFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthorizationManager<RequestAuthorizationContext> authz) throws Exception {
        // @formatter:off
        http
            .httpBasic((httpSecurityConfig)->{
                httpSecurityConfig.disable();
            })
            .formLogin((httpSecurityConfig) -> {httpSecurityConfig.disable();})
            .csrf((httpSecurityConfig) -> { httpSecurityConfig.disable();})
            .logout((logoutConfig) -> {logoutConfig.disable();})
            .sessionManagement((session) -> {
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            })
            .authorizeHttpRequests((authorizeHttpRequests) -> {
                authorizeHttpRequests
                    .requestMatchers(RegexRequestMatcher.regexMatcher("/user/[0-9]+")).hasAuthority("ROLE_ADMIN")
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .anyRequest().access(authz)
                    ;
            })
            .exceptionHandling((exceptionHandling) -> {
                exceptionHandling
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                    // .accessDeniedHandler(jwtAccessDeniedHandler)
                    ;
            })
            // .exceptionHandling(Customizer.withDefaults())
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
            ;
		// @formatter:on
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // DaoAuthenticationProvider 从自定义的 userDetailsService.loadUserByUsername
        // 方法获取UserDetails
        authProvider.setUserDetailsService(userDetailsService());
        // 设置密码编辑器
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public UserDetailsService userDetailsService() {
        return (username) -> userDetailsService.loadUserByUsername(username);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> {
            web.ignoring().requestMatchers(URL_WHITE_URL);
        };
    }

    @Bean
    static GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("ROLE_");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationEventPublisher authenticationEventPublisher(
            ApplicationEventPublisher applicationEventPublisher) {
        DefaultAuthenticationEventPublisher authenticationEventPublisher = new DefaultAuthenticationEventPublisher(
                applicationEventPublisher);
        // authenticationEventPublisher.setAdditionalExceptionMappings(new HashMap<>() {
        // {
        // put(BadCredentialsException.class,
        // AuthenticationFailureBadCredentialsEvent.class);
        // }
        // });
        return authenticationEventPublisher;
    }

}

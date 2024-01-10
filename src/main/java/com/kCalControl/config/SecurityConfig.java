package com.kCalControl.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final JwtFilter filter;

    private final TokenRevocationService tokenRevocationService;

    @Autowired
    public SecurityConfig(JwtFilter filter, TokenRevocationService tokenRevocationService) {
        this.filter = filter;
        this.tokenRevocationService = tokenRevocationService;
    }

    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.csrfTokenRepository(csrfTokenRepository())
                        .ignoringRequestMatchers("/mobile/**"))
                .cors((cors) -> cors.configurationSource(request -> {
                    var corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(List.of("*"));
                    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
                    corsConfiguration.setAllowedHeaders(List.of("*"));
                    return corsConfiguration;
                }))
                .authorizeHttpRequests(c -> c.requestMatchers("/authenticate", "/signup", "/error").permitAll()
                        .requestMatchers("/**", "/mobile/**").authenticated())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

        http.addFilterBefore(this.filter, UsernamePasswordAuthenticationFilter.class);

        http
                .logout((httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.logoutUrl("/logout").invalidateHttpSession(true).logoutSuccessHandler((request, response, authentication) -> {
                    try {
                        var token = filter.extractTokenFromRequest(request);
                        tokenRevocationService.revokeToken(token);
                        response.setStatus(HttpServletResponse.SC_OK);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).permitAll()));
        return http.build();
    }

}

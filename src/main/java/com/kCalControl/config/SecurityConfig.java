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
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
                .csrf((csrf) -> csrf.csrfTokenRepository(new HttpSessionCsrfTokenRepository()).ignoringRequestMatchers("/api/authenticate", "/api/user/signup", "/error"))
                .cors((cors) -> cors.configurationSource(request -> {
                    var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(java.util.List.of("*"));
                    corsConfiguration.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE"));
                    corsConfiguration.setAllowedHeaders(java.util.List.of("*"));
                    return corsConfiguration;
                }))
                .authorizeHttpRequests(c -> c.requestMatchers("/api/authenticate", "/api/user/signup", "/error").permitAll()
                .requestMatchers("/api/**").authenticated())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

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

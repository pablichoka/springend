package com.kCalControl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Checker checker(){return new Checker();};

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{

        http
                .authorizeHttpRequests()
                .requestMatchers("/auth/api/**", "/auth/views/**").hasAnyRole("ADMIN","USER")
                .requestMatchers("/auth/admin/**").hasRole("ADMIN")
                .requestMatchers("/noAuth/**", "/", "/css/**", "/js/**", "/error/**", "/img/**").permitAll()
                .requestMatchers("/auth/**").authenticated()
                .and()
            .formLogin()
    //            .loginPage("/actions/login")
                .defaultSuccessUrl("/views/home")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

        return http.build();
    }

}

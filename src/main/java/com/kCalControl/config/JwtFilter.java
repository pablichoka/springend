package com.kCalControl.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final static Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    TokenManager tokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            logger.debug("Bearer Authorization required: {}", authorizationHeader);
            filterChain.doFilter(request, response);
            return;
        }

        var token = authorizationHeader.substring(7);
        Claims claims;
        try {
            claims = this.tokenManager.parseToken(token);
        } catch (RuntimeException e) {
            logger.debug("Token {} invalid", token, e);
            // TODO: change status response from 403 to 401
            filterChain.doFilter(request, response);
            return;
        }
        if (!this.tokenManager.validateClaims(claims)) {
            logger.debug("Token {} invalid", token);
            filterChain.doFilter(request, response);
            return;
        }

        String subject;
        try {
            subject = this.tokenManager.getSubject(claims);
        } catch (Exception e) {
            logger.debug("Token {} invalid", token, e);
            filterChain.doFilter(request, response);
            return;
        }

        Integer personId;
        try {
            personId = Integer.parseInt(subject);
        } catch (NumberFormatException e) {
            logger.debug("Token {} not valid", token, e);
            filterChain.doFilter(request, response);
            return;
        }
        AbstractAuthenticationToken authentication = new AbstractAuthenticationToken(null) {

            private static final long serialVersionUID = 1L;

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return personId;
            }
        };
        authentication.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        var path = request.getRequestURI();
        return "/authenticate".equals(path);
    }
}

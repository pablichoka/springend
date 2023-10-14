package com.kCalControl.config;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final static Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    TokenManager tokenManager;

    @Autowired
    TokenRevocationService tokenRevocationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain)
            throws ServletException, IOException {

        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            logger.debug("Bearer Authorization required: {}", authorizationHeader);
            filterChain.doFilter(request, response);
            return;
        }

        var token = authorizationHeader.substring(7);

        if(tokenRevocationService.isTokenRevoked(token)){
            response.sendError(401, "Token expired, please login to create a new one");
            return;
        }

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

        ObjectId personId;
        try {
            personId = new ObjectId(subject);
        } catch (NumberFormatException e) {
            logger.debug("Token {} not valid", token, e);
            filterChain.doFilter(request, response);
            return;
        }

        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(subject);
        } catch (UsernameNotFoundException e) {
            logger.debug("User not found {} not valid", subject, e);
            filterChain.doFilter(request, response);
            return;
        }

        List<GrantedAuthority> authorities = new ArrayList<>(userDetails.getAuthorities());

        AbstractAuthenticationToken authentication = new AbstractAuthenticationToken(authorities) {

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
        logger.debug("Estas son la autorities: " + authorities.toString());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        var path = request.getRequestURI();
        return "/authenticate".equals(path);
    }

    protected String extractTokenFromRequest(HttpServletRequest request) throws Exception {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }else{
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }
}

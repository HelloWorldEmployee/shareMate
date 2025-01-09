package com.sharemate.webservice.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sharemate.webservice.domain.UserEntity;
import com.sharemate.webservice.service.CustomUserDetails;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTFilter extends OncePerRequestFilter{
    
    private final JWTUtill jwtUtill;

    public JWTFilter(JWTUtill jwtUtill) {
        this.jwtUtill = jwtUtill;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        System.out.println("authorization : " + authorization);
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            System.out.println("token null");
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println("authorization now");
        
        String token = authorization.split(" ")[1];
        System.out.println("token : " + token);

        if(jwtUtill.isExpired(token)) {
            System.out.println("token expired");
            filterChain.doFilter(request, response);
            return; 
        }

        String userId = jwtUtill.getUserId(token);
        String userRole = jwtUtill.getRole(token);

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        userEntity.setUserPassword("temppassword");
        userEntity.setUserRole(userRole);
        System.out.println("유저 엔티티 JWT fIlter 생성 : " + userEntity);
        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        System.out.println("authToken : " + authToken);
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}

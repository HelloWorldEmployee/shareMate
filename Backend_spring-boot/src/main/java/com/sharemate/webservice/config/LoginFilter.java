package com.sharemate.webservice.config;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sharemate.webservice.domain.CustomUserDetails;
import com.sharemate.webservice.domain.UserEntity;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

        private final AuthenticationManager authenticationManager;
        private final JWTUtil jwtUtil;

        public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
                this.authenticationManager = authenticationManager;
                this.jwtUtil = jwtUtil;
        }

        @Override
        public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
                        throws AuthenticationException {

                try {
                        // JSON 요청 본문을 파싱(Postman 요청 본문에서 JSON 데이터를 직접 파싱)
                        ObjectMapper objectMapper = new ObjectMapper();
                        UserEntity user = objectMapper.readValue(request.getInputStream(), UserEntity.class);

                        // 클라이언트 요청에서 username, password 추출
                        String userId = user.getUserId();
                        System.out.println("userId : " + userId);

                        // String userName = obtainUsername(request);
                        // System.out.println("userName : " + userName);

                        String userPassword = user.getUser_password();
                        System.out.println("userPassword : " + userPassword);

                        // 스프링 시큐리티에서 userId, userPassword를 검증하기 위해서는 token에 담아야함.
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userId,
                                        userPassword,
                                        null);

                        // token에 담은 검증을 위한 AuthenticationManager로 전달
                        return authenticationManager.authenticate(authToken);
                } catch (IOException e) {
                        throw new RuntimeException("Failed to parse request body", e);
                }
        }

        // 로그인 성공시 실행(JWT발급 하는 곳)
        @Override
        protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                        FilterChain chain,
                        Authentication authentication) {

                // UserDetailsS
                CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

                String userId = customUserDetails.getUserId();

                Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
                GrantedAuthority auth = iterator.next();

                String role = auth.getAuthority();

                String token = jwtUtil.createJwt(userId, role, 60 * 60 * 10L);

                response.addHeader("Authorization", "Bearer " + token);
        }

        // 로그인 실패시 실행
        @Override
        protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                        AuthenticationException failed) {

                // 로그인 실패시 401 응답 코드 반환
                response.setStatus(401);
        }
}
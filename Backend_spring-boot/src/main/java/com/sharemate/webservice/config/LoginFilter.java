package com.sharemate.webservice.config;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.sharemate.webservice.service.CustomUserDetails;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtill jwtUtill;

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtill jwtUtill) {

        this.authenticationManager = authenticationManager;
        this.jwtUtill = jwtUtill;

        //로그인 url 특정
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/user/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            // // JSON 요청 본문을 파싱(Postman 요청 본문에서 JSON 데이터를 직접 파싱)
            // ObjectMapper objectMapper = new ObjectMapper();
            // UserEntity user = objectMapper.readValue(request.getInputStream(),
            // UserEntity.class);

            // 클라이언트 요청에서 username, password 추출
            // String userId = user.getUserId();
            // System.out.println("userId : " + userId);
            // String userPassword = user.getUserPassword();
            // System.out.println("userPassword : " + userPassword);

            // String userId = obtainUsername(request);
            // String userPassword = obtainPassword(request);
            String userId = request.getParameter("userId");
            String userPassword = request.getParameter("userPassword");

            // System.out.println("LoginFilter!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + " / " + request.getParameter("userId") + " / " + userId + " / " + userPassword);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userId, userPassword, null);

            return authenticationManager.authenticate(authToken);
        } catch (Exception e) {
            System.out.println("error : " + e.getMessage());
            throw e;
        }

    }

    //로그인 성공시 실행(JWT발급 하는 곳)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String userId = customUserDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String userRole = auth.getAuthority();

        String token = jwtUtill.createJwt(userId, userRole, 60 * 60 * 10L);

        response.addHeader("Authorization", "Bearer " + token);

        System.out.println("Success");
    }

    //로그인 실패시 실행
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        System.out.println("Fail");
        response.setStatus(401);
    }

}

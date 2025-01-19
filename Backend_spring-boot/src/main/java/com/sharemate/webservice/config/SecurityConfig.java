package com.sharemate.webservice.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtill jwtUtill;

    // AuthenticationManger가 인자로 받을 AuthenticationConfiguration 객체 생성자 주입
    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtill jwtUtill) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtill = jwtUtill;
    }

    // AuthenticationManager 빈 주입
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // cors
        http
                .cors((cors) -> cors
                        .configurationSource(new CorsConfigurationSource() {

                            @Override
                            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                                CorsConfiguration configuration = new CorsConfiguration();

                                configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                                configuration.setAllowedMethods(Collections.singletonList("*"));
                                configuration.setAllowCredentials(true);
                                configuration.setAllowedHeaders(Collections.singletonList("*"));
                                configuration.setMaxAge(3600L);
                                configuration.setExposedHeaders(Collections.singletonList("Authorization"));

                                return configuration;
                            }

                        }));
        // csrf 공격 막는거 일단 품 (jwt라서 필요없음)(stateless 방식이라)
        http
                .csrf((auth) -> auth.disable())
                // 폼 기반 로그인 안씀 jwt쓸거니까
                // http
                .formLogin((auth) -> auth.disable());

        // authorization 헤더에 아이디 비번 포함안시킴
        http
                .httpBasic((auth) -> auth.disable());
        // 경로 인가 작업
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/api/user/login", "/", "/api/user",
                                "/api/user/{userId}", "/api/user/{userId}/{userPassword}",
                                "/api/competition", "/api/competition/{compId}")
                        .permitAll()
                        .requestMatchers("/api/study").hasRole("USER") // 이면 접두사 Role_ 필요

                        // .requestMatchers(HttpMethod.POST, "/api/competition").hasRole("ADMIN")
                        // .requestMatchers(HttpMethod.PUT,
                        // "/api/competition/{compId}").hasRole("ADMIN")
                        // .requestMatchers(HttpMethod.DELETE,
                        // "/api/competition/{compId}").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/compJoin/{compId}").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/compJoin/{compId}/{userId}").hasRole("USER")
                        .anyRequest().authenticated());

        // JWT FIlter
        http
                .addFilterBefore(new JWTFilter(jwtUtill), LoginFilter.class);
        // 커스텀 로그인 필터
        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtill),
                        UsernamePasswordAuthenticationFilter.class);
        // 서버에서 세션생성안할거임 jwt
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}

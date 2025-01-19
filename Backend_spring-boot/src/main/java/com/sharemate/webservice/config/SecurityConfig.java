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

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        // AuthenticationManager가 인자로 받을 AuthenticationConfiguration 객체 생성자 주입
        private final AuthenticationConfiguration authenticationConfiguration;
        private final JWTUtill jwtUtill;

        // AuthenticationManger가 인자로 받을 AuthenticationConfiguration 객체 생성자 주입
        public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtill jwtUtill) {
                this.authenticationConfiguration = authenticationConfiguration;
                this.jwtUtill = jwtUtill;
        }

        // AuthenticationManager Bean 등록
        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
                return configuration.getAuthenticationManager();
        }

        // 비밀번호를 캐시로 암호화
        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder() {

                return new BCryptPasswordEncoder();
        }

        // LoginFilter 객체에 주입
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                // csrf disable - 세션에서 항상 세션 고정으로 csrf 공격을 필수적으로 방어, JWT는 stateless 상태로 관리함으로
                // csrf 공격을 방어하지 않아 기본 상태로 설정(disable)
                http
                                .csrf((auth) -> auth.disable());

                // Form 로그인 방식 disable(jwt 사용으로 불필요)
                http
                                .formLogin((auth) -> auth.disable());

                // http basic 인증 방식 disable(authorization 헤더에 id,pw 불포함)
                http
                                .httpBasic((auth) -> auth.disable());

                // 경로별 인가 작업
                http.authorizeHttpRequests((auth) -> auth
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
                                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration),
                                                jwtUtill),
                                                UsernamePasswordAuthenticationFilter.class);
                // 세션 설정(jwt로 로그인)
                http
                                .sessionManagement((session) -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                // cors 설정
                http
                                .cors(cors -> cors.configurationSource(request -> {
                                        CorsConfiguration config = new CorsConfiguration();
                                        config.setAllowCredentials(true);
                                        config.addAllowedOrigin("http://localhost:3000");
                                        config.addAllowedHeader("*");
                                        config.addAllowedMethod("*");
                                        config.setExposedHeaders(Collections.singletonList("Authorization"));
                                        config.setMaxAge(3600L);

                                        return config;
                                }));

                return http.build();
        }

}

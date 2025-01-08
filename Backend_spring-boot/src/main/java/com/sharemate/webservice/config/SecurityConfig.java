package com.sharemate.webservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        // 비밀번호를 캐시로 암호화
        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder() {

                return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                // csrf diable - 세션에서 항상 세션 고정으로 csrf 공격을 필수적으로 방어, JWT는 stateless 상태로 관리함으로
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
                                .requestMatchers("/Api/User/login", "/", "/join").permitAll()
                                .requestMatchers("/admin").hasRole("ADMIN")
                                .anyRequest().authenticated());

                // 세션 설정(jwt로 로그인)
                http
                                .sessionManagement((session) -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

                http
                                .cors(cors -> cors.configurationSource(request -> {
                                        CorsConfiguration config = new CorsConfiguration();
                                        config.setAllowCredentials(true);
                                        config.addAllowedOrigin("*");
                                        config.addAllowedHeader("*");
                                        config.addAllowedMethod("*");
                                        return config;
                                }));

                return http.build();
        }

}

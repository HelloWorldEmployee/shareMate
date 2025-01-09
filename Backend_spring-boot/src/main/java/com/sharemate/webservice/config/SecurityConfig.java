package com.sharemate.webservice.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        private final JWTUtil jwtUtil;

        public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil) {
                this.authenticationConfiguration = authenticationConfiguration;
                this.jwtUtil = jwtUtil;
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
                                .requestMatchers("/login", "/", "/join").permitAll()
                                .requestMatchers("/admin").hasRole("ADMIN")
                                .anyRequest().authenticated());

                http
                                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);
                http
                                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration),
                                                jwtUtil),
                                                UsernamePasswordAuthenticationFilter.class);

                // 세션 설정(jwt로 로그인)
                http
                                .sessionManagement((session) -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

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

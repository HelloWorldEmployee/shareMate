    package com.sharemate.webservice.Confi;

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


    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {

        private final AuthenticationConfiguration authenticationConfiguration;
        private final JWTUtill jwtUtill;

        //AuthenticationManger가 인자로 받을 AuthenticationConfiguration 객체 생성자 주입
        public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtill jwtUtill) {
            this.authenticationConfiguration = authenticationConfiguration;
            this.jwtUtill = jwtUtill;
        }

        //AuthenticationManager 빈 주입
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

            // csrf 공격 막는거 일단 품 (jwt라서 필요없음)(stateless 방식이라)
            http
                .csrf((auth) -> auth.disable())
            //폼 기반 로그인 안씀 jwt쓸거니까
            // http
                .formLogin((auth) -> auth.disable());

            //authorization 헤더에 아이디 비번 포함안시킴
            http
                .httpBasic((auth) -> auth.disable());
            //경로 인가 작업
            http   
                .authorizeHttpRequests((auth) -> auth
                    .requestMatchers("/api/user/login", "/", "/api/user").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/study").hasRole("USER") //이면 접두사 Role_ 필요
                    .anyRequest().authenticated()
                    );
            //JWT FIlter
            http
                .addFilterBefore(new JWTFilter(jwtUtill), LoginFilter.class);
            //커스텀 로그인 필터 
            http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtill), UsernamePasswordAuthenticationFilter.class);
            //서버에서 세션생성안할거임 jwt
            http
                .sessionManagement((session) -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    );

            return http.build();
        }
    }

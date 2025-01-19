package com.sharemate.webservice.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.sharemate.webservice.domain.UserEntity;
import com.sharemate.webservice.domain.UserRepository;

//@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUserId(userId); // userId로 사용자를 찾음
        System.out.println("유저 아이디 찾기 메소드 실행 !!!!!!!!!!! " + userId + " / " + user);

        if (user != null) {
            System.out.println("커스텀유저서비스 유저 받기 실행 성공!! " + user);
            // UserDetails에 담아서 AuthenticationManager가 검증함
            return new CustomUserDetails(user);
        }

        throw new UsernameNotFoundException("유저 아이디 찾기 실패 " + userId);
    }
}

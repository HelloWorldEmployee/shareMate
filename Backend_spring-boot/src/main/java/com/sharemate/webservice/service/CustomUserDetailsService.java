package com.sharemate.webservice.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sharemate.webservice.domain.CustomUserDetails;
import com.sharemate.webservice.domain.UserEntity;
import com.sharemate.webservice.domain.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails findUserByUserId(String userId) throws UsernameNotFoundException {

        UserEntity userData = userRepository.findByUserId(userId);

        if (userData != null) {

            // UserDetails에 담아서 AuthenticationManager가 검증함
            return new CustomUserDetails(userData);
        }

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        UserEntity userData = userRepository.findByUserId(userId); // userId로 사용자를 찾음

        if (userData != null) {
            return new CustomUserDetails(userData);
        }

        throw new UsernameNotFoundException("User not found with ID: " + userId);
        // UserEntity userData = userRepository.findByUserName(userName);

        // if (userData != null) {

        // UserDetails에 담아서 AuthenticationManager가 검증함
        // return new CustomUserDetails(userData);
        // }

        // return null;
    }
}

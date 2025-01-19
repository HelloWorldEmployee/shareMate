package com.sharemate.webservice.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sharemate.webservice.domain.UserEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails{
    
    private final UserEntity userEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {
                System.out.println("유저 롤 겟 : " + userEntity.getUserRole());
                return userEntity.getUserRole();
            }
        });
        System.out.println("커스텀유저디테일 : " + collection);
        return collection;
    }

    @Override
    public String getPassword() {
        return userEntity.getUserPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUserId();
    }
  
    public String getUserId() {
        return userEntity.getUserId();
    }


    @Override
    public boolean isAccountNonExpired() {
        // 계정이 만료되지 않았는지 체크 (예시: 만료되지 않았다면 true)
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정이 잠겨 있지 않은지 체크 (예시: 잠겨 있지 않으면 true)
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 자격 증명이 만료되지 않았는지 체크 (예시: 만료되지 않았다면 true)
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 계정이 활성화되었는지 체크 (예시: 활성화되었다면 true)
        return true;
    }
}

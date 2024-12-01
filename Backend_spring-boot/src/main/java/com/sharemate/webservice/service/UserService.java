package com.sharemate.webservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sharemate.webservice.domain.UserEntity;
import com.sharemate.webservice.domain.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //final 생성자 자동생성
@Service
public class UserService {
    
    private final UserRepository userRepository;

    @Transactional //트랜잭션 관리 
    public UserEntity userCreate(UserEntity user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly=true)
    public List<UserEntity> allRead() {
        return userRepository.findAll();
    }

    @Transactional
    public UserEntity userUpdate(String id, UserEntity user) {
        //더티채킹
        UserEntity userData = userRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("'id'를 확인해주세요!"));
        userData.setUser_email(user.getUser_email());
        userData.setUser_email(user.getUser_email());

        return userData;
    }

    @Transactional
    public String userDelete(String id, String password) {
        userRepository.deleteById(id);
        return "success";
    }
}

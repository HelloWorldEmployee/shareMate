package com.sharemate.webservice.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sharemate.webservice.domain.UserEntity;
import com.sharemate.webservice.domain.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //final 생성자 자동생성 -> autowired 필요없음
@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional //트랜잭션 관리 
    public UserEntity userCreate(UserEntity user) {
        System.out.println("user : " + user);
        user.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));
        user.setUserRole("ROLE_USER");
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public boolean checkUserIdExists(String userId) {
        return userRepository.existsById(userId);
    }

    @Transactional(readOnly = true)
    public List<UserEntity> allRead() {
        return userRepository.findAll();
    }

    @Transactional //업데이트로 Role규정 
    public UserEntity userUpdate(String id, UserEntity user) {
        // 더티채킹
        UserEntity userData = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("'id'를 확인해주세요!"));
        userData.setUserEmail(user.getUserEmail());
        // userData.setUserEmail(user.getUserEmail());

        return userData;
    }

    @Transactional
    public String userDelete(String id, String password) {
        userRepository.deleteById(id);
        return "success";
    }
}

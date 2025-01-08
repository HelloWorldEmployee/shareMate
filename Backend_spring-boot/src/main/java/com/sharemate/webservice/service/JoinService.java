package com.sharemate.webservice.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sharemate.webservice.domain.UserEntity;
import com.sharemate.webservice.domain.UserRepository;

@Service
public class JoinService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public boolean joinProcess(UserEntity userEntity) {
        String user_id = userEntity.getUserId();
        String user_name = userEntity.getUser_name();
        String password = userEntity.getUser_password();
        String user_email = userEntity.getUser_email();

        Boolean isExist = userRepository.existsByUserId(user_id);
        if (isExist) {
            return false;
        }

        UserEntity data = new UserEntity();
        data.setUserId(user_id);
        data.setUser_name(user_name);
        // PW는 암호화하여 사용.
        data.setUser_password(bCryptPasswordEncoder.encode(password));
        data.setUser_email(user_email);
        data.setUser_role("ROLE_ADMIN");

        userRepository.save(data);
        return true;
    }
}

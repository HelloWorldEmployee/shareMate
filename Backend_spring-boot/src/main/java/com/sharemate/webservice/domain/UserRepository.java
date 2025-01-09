package com.sharemate.webservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    Boolean existsByUserId(String userId);

    // UserEntity findByUserName(String userName);

    UserEntity findByUserId(String userId);
}

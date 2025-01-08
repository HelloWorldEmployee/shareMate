package com.sharemate.webservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    Boolean existsByUserId(String userId);
}

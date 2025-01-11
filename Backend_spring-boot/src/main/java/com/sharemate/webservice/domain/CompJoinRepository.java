package com.sharemate.webservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompJoinRepository extends JpaRepository<CompJoinEntity, Integer> {

    void findByJoinIdAndUserId(int compId, String userId);
}

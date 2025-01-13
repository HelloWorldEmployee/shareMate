package com.sharemate.webservice.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompJoinRepository extends JpaRepository<CompJoinEntity, Integer> {

    Boolean existsByCompId(int compId);

    List<CompJoinEntity> findByCompId(int compId);

    CompJoinEntity findByCompIdAndUserId(int compId, String userId);
}

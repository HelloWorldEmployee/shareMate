package com.sharemate.webservice.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<StudyEntity, Integer>{
    List<StudyEntity> findByUserId(String user_id);
    void deleteByStudyIdAndUserId(int id, String user_id);
}

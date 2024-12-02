package com.sharemate.webservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyCommentRepository extends JpaRepository<StudyCommentEntity, Integer>{
    void deleteByStudyCommentIdAndUserId(int studyCommentId, String userId); //JPA에 쓸라면 deleteBy로 시작해야됨 -> 규칙있음
    // void deleteByStudy_comment_idAndUser_id(int studyCommentId, String userId);

}

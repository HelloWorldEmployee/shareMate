package com.sharemate.webservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class StudyCommentEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int study_comment_id;

    @Lob // 큰 데이터 처리
    private String study_comment_content;


}
 
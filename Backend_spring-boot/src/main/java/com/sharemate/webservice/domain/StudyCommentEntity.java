package com.sharemate.webservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "study_comment")
public class StudyCommentEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "study_comment_id")
    private int studyCommentId;

    @Lob // 큰 데이터 처리
    public String study_comment_content;

    public int study_id;

    @Column(name = "user_id") //JPA직접 메소드 만들때는 자동으로 스네이크케이스 인식안함
                              //(어플리케이션 속성에 DB이름 그대로 쓰겠다는 옵션써도 안됨)
                              // 지정해줘야됨
    public String userId;

}
 
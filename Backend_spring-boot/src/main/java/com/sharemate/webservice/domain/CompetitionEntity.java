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

@AllArgsConstructor // 생성자(매개변수있는것) 자동 생성
@NoArgsConstructor // 기본생성자 자동생성
@Data // setter, getter, tostring 등 자동생성
@Entity
@Table(name = "competition")
public class CompetitionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comp_id")
    private int compId;

    private String comp_title;

    @Lob
    private String comp_content;

    @Column(name = "user_id")
    private String useId;

    private int count;
}

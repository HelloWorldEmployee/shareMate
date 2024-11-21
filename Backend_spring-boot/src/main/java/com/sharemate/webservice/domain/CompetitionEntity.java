package com.sharemate.webservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private int comp_id;

    private String comp_title;

    private String comp_content;

    private String user_id;

    private int count;
}

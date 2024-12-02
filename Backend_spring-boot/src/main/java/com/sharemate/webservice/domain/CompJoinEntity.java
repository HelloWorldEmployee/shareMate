package com.sharemate.webservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data // setter, getter, tostring 등 자동생성
@Entity
@Table(name = "comp_join")
public class CompJoinEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "join_id")
    private int joinId;

    @Column(name = "comp_id")
    private int compId;

    @Column(name = "user_id")
    private String userId;
}

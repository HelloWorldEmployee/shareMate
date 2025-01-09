package com.sharemate.webservice.domain;

import jakarta.persistence.Column;
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
@Table(name = "user")
public class UserEntity {
    @Id
    @Column(name = "user_id")
    private String userId;

    private String user_password;

    @Column(name = "user_name")
    public String userName;

    public String user_email;

    private String user_role;
}

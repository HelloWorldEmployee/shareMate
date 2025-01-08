package com.sharemate.webservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharemate.webservice.domain.UserEntity;
import com.sharemate.webservice.service.JoinService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@ResponseBody
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @PostMapping("/join")
    public ResponseEntity<?> joinProcess(@RequestBody UserEntity userEntity) {

        if (userEntity.getUser_password() == null) {
            return ResponseEntity.badRequest().body("비밀번호가 필요합니다.");
        }

        boolean isJoined = joinService.joinProcess(userEntity);
        if (!isJoined) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("사용자가 이미 존재합니다.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공!");
    }

}

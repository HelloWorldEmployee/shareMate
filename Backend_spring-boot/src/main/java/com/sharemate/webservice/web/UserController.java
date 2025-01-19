package com.sharemate.webservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sharemate.webservice.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sharemate.webservice.domain.UserEntity;
import com.sharemate.webservice.domain.UserRepository;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // cors 회피
@RestController
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    // 회원가입

    @PostMapping("/api/user")
    public ResponseEntity<?> save(@RequestBody UserEntity user) {
        Boolean isExistId = userRepository.existsById(user.getUserId());
        if (isExistId) {
            return ResponseEntity.badRequest().body("아이디가 이미 존재합니다.");
        }
        if (user.getUserPassword() == null) {
            return ResponseEntity.badRequest().body("비밀번호가 필요합니다.");
        }

        return new ResponseEntity<>(userService.userCreate(user), HttpStatus.CREATED);
    }

    // 아이디 중복확인
    @GetMapping("/api/user/{id}")
    public ResponseEntity<?> checkUserId(@PathVariable String id) {
        boolean isExist = userService.checkUserIdExists(id);
        if (isExist) {
            return ResponseEntity.badRequest().body("아이디가 이미 존재합니다.");
        }
        return ResponseEntity.ok("사용 가능한 아이디 입니다.");
    }

    // JWT로 로그인 구현
    // @PostMapping("/api/user/login")
    // public ResponseEntity<?> login(@RequestBody UserEntity userEntity) {
    // System.out.println("로그인 : " + userEntity);
    // return ResponseEntity.ok("로그인 성공");
    // }

    // 계정 전체조회
    @GetMapping("/api/user")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(userService.allRead(), HttpStatus.OK);
    }

    // 계정 수정
    @PutMapping("/api/user/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UserEntity user) {
        return new ResponseEntity<>(userService.userUpdate(id, user), HttpStatus.OK);
    }

    // 계정 삭제
    @DeleteMapping("/api/user/{id}/{password}")
    public ResponseEntity<?> delete(@PathVariable String id, @PathVariable String password) {
        return new ResponseEntity<>(userService.userDelete(id, password), HttpStatus.OK);
    }

}

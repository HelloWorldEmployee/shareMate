package com.sharemate.webservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // cors 회피
@RestController
// @RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/api/user")
    public ResponseEntity<?> save(@RequestBody UserEntity user) {
        System.out.println("회원가입 api 불러오기 성공!");
        return new ResponseEntity<>(userService.userCreate(user), HttpStatus.CREATED);
    }

    // 아이디 중복 체크(회원가입)
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
        String currentId = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("아이디 : " + currentId);
        return new ResponseEntity<>(userService.userDelete(id, password), HttpStatus.OK);
    }

}

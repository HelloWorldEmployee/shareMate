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
import org.springframework.web.bind.annotation.RequestMapping;

import com.sharemate.webservice.domain.UserEntity;

@RequiredArgsConstructor
@RestController
// @RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @CrossOrigin // cors 회피
    @PostMapping("/api/user")
    public ResponseEntity<?> save(@RequestBody UserEntity user) {
        return new ResponseEntity<>(userService.userCreate(user), HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping("/api/user/{id}")
    public ResponseEntity<?> checkUserId(@PathVariable String id) {
        boolean isExist = userService.checkUserIdExists(id);
        if (isExist) {
            return ResponseEntity.badRequest().body("아이디가 이미 존재합니다.");
        }
        return ResponseEntity.ok("사용 가능한 아이디 입니다.");
    }

    // @PostMapping("/api/user/login")
    // public ResponseEntity<?> login(@RequestBody UserEntity userEntity) {
    //     System.out.println("로그인 : " + userEntity);
    //     return ResponseEntity.ok("로그인 성공");
    // }
    
    @CrossOrigin // cors 회피
    @GetMapping("/api/user")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(userService.allRead(), HttpStatus.OK);
    }

    @CrossOrigin // cors 회피
    @PutMapping("/api/user/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UserEntity user) {
        return new ResponseEntity<>(userService.userUpdate(id, user), HttpStatus.OK);
    }

    @CrossOrigin // cors 회피
    @DeleteMapping("/api/user/{id}/{password}")
    public ResponseEntity<?> delete(@PathVariable String id, @PathVariable String password) {
        return new ResponseEntity<>(userService.userDelete(id, password), HttpStatus.OK);
    }

}

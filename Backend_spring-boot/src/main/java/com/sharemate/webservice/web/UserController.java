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


@RequiredArgsConstructor
@RestController
public class UserController {
    
    private final UserService userService;

    @CrossOrigin //cors 회피
    @PostMapping("/user")
    public ResponseEntity<?> save(@RequestBody UserEntity user) {
        return new ResponseEntity<>(userService.userCreate(user), HttpStatus.CREATED);
    }

    @CrossOrigin //cors 회피
    @GetMapping("/user")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(userService.allRead(), HttpStatus.OK);
    }

    @CrossOrigin //cors 회피
    @PutMapping("/user/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UserEntity user) {
        return new ResponseEntity<>(userService.userUpdate(id, user), HttpStatus.OK);
    }

    @CrossOrigin //cors 회피
    @DeleteMapping("/user/{id}/{password}")
    public ResponseEntity<?> delete(@PathVariable String id, @PathVariable String password) {
        return new ResponseEntity<>(userService.userDelete(id, password), HttpStatus.OK);
    }
    
    
}

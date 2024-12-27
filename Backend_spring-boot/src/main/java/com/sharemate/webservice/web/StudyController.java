package com.sharemate.webservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sharemate.webservice.domain.StudyEntity;
import com.sharemate.webservice.service.StudyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class StudyController {
    private final StudyService studyService;

    @CrossOrigin
    @PostMapping("/api/study")
    public ResponseEntity<?> save(@RequestBody StudyEntity study) {
        return new ResponseEntity<>(studyService.studyCreate(study), HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping("/api/study")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(studyService.allRead(), HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping("/api/study/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody StudyEntity study) {
        return new ResponseEntity<>(studyService.studyUpdate(id, study), HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/api/study/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>(studyService.studyDelete(id), HttpStatus.OK);
    }
    // @CrossOrigin
    // @DeleteMapping("/api/study/{id}/{user_id}")
    // public ResponseEntity<?> delete(@PathVariable int id, @PathVariable String user_id) {
    //     return new ResponseEntity<>(studyService.studyDelete(id, user_id), HttpStatus.OK);
    // }





}

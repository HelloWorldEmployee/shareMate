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

import com.sharemate.webservice.domain.StudyCommentEntity;
import com.sharemate.webservice.service.StudyCommentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class StudyCommentController {
    private final StudyCommentService studyCommentService;

    @CrossOrigin
    @PostMapping("/studyComment")
    public ResponseEntity<?> save(@RequestBody StudyCommentEntity studyComment) {
        return new ResponseEntity<>(studyCommentService.studyCommentCreate(studyComment), HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping("/studyComment")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(studyCommentService.allRead(), HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping("/studyComment/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody StudyCommentEntity studyComment) {
        return new ResponseEntity<>(studyCommentService.studyCommentUpdate(id, studyComment), HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/studyComment/{id}/{user_id}")
    public ResponseEntity<?> delete(@PathVariable int id, @PathVariable String user_id) {
        return new ResponseEntity<>(studyCommentService.studyCommentDelete(id, user_id), HttpStatus.OK);
    }

}

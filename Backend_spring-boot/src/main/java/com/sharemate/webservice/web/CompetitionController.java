package com.sharemate.webservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sharemate.webservice.domain.CompetitionEntity;
import com.sharemate.webservice.service.CompetitionService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@RestController
public class CompetitionController {

    @Autowired
    private final CompetitionService competitionService;

    @CrossOrigin // cors 회피
    @PostMapping("/api/competition")
    public ResponseEntity<?> save(@RequestBody CompetitionEntity competition) {

        return new ResponseEntity<>(competitionService.competitionCreate(competition), HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping("/api/competition")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(competitionService.allList(), HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping("/api/competition/{compId}")
    public ResponseEntity<?> update(@PathVariable String userId, @RequestBody CompetitionEntity competition) {
        return new ResponseEntity<>(competitionService.competitionUpdate(userId, competition), HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/api/competition/{compId}")
    public ResponseEntity<?> delete(@PathVariable int compId) {
        return new ResponseEntity<>(competitionService.competitionDelete(compId), HttpStatus.OK);
    }

}

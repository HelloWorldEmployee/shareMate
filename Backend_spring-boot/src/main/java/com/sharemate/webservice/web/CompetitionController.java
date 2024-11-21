package com.sharemate.webservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sharemate.webservice.domain.CompetitionEntity;
import com.sharemate.webservice.service.CompetitionService;

import io.micrometer.core.ipc.http.HttpSender.Response;
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

    private final CompetitionService competitionService;

    @CrossOrigin // cors 회피
    @PostMapping("/competition")
    public ResponseEntity<?> save(@RequestBody CompetitionEntity competition) {

        return new ResponseEntity<>(competitionService.competitionCreate(competition), HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping("/competition")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(competitionService.allList(), HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping("/competition")
    public ResponseEntity<?> update(@PathVariable String user_id, @RequestBody CompetitionEntity competition) {
        return new ResponseEntity<>(competitionService.competitionUpdate(user_id, competition), HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/competition")
    public ResponseEntity<?> delete(@PathVariable String user_id, @PathVariable int comp_id) {
        return new ResponseEntity<>(competitionService.competitionDelete(user_id, comp_id), HttpStatus.OK);
    }

}

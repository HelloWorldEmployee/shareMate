package com.sharemate.webservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sharemate.webservice.domain.CompJoinEntity;
import com.sharemate.webservice.service.CompJoinService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CompJoinController {

    @Autowired
    private final CompJoinService compJoinService;

    @CrossOrigin // cors 회피
    @PostMapping("/api/compJoin")
    public ResponseEntity<?> save(@RequestBody CompJoinEntity compJoin) {

        return new ResponseEntity<>(compJoinService.compJoinCreate(compJoin), HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping("/api/compJoin")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(compJoinService.compJoinSelect(), HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/api/compJoin/{compId}/{userId}")
    public ResponseEntity<?> delete(@PathVariable int joinId, @PathVariable String userId) {
        return new ResponseEntity<>(compJoinService.compJoinDelete(joinId, userId), HttpStatus.OK);
    }
}

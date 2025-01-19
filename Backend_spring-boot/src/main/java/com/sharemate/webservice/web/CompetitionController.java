package com.sharemate.webservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sharemate.webservice.domain.CompetitionEntity;
import com.sharemate.webservice.domain.CompetitionRepository;
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
@CrossOrigin // cors 회피
public class CompetitionController {

    @Autowired
    private final CompetitionService competitionService;
    @Autowired
    private final CompetitionRepository competitionRepository;

    @PostMapping("/api/competition")
    public ResponseEntity<?> save(@RequestBody CompetitionEntity competition) {

        return new ResponseEntity<>(competitionService.competitionCreate(competition), HttpStatus.CREATED);
    }

    @GetMapping("/api/competition")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(competitionService.allList(), HttpStatus.OK);
    }

    // compId 1개 조회
    @GetMapping("/api/competition/{compId}")
    public ResponseEntity<?> findCompId(@PathVariable int compId) {
        boolean isExist = competitionRepository.existsByCompId(compId);
        if (!isExist) {
            return ResponseEntity.badRequest().body("해당 공모전 게시글이 없습니다.");
        }
        return new ResponseEntity<>(competitionService.searchCompetition(compId), HttpStatus.OK);
    }

    @PutMapping("/api/competition/{compId}")
    public ResponseEntity<?> update(@PathVariable int compId, @RequestBody CompetitionEntity competition) {

        // compId에 해당하는 CompetitionEntity가 존재하는지 데이터베이스에서 조회
        Boolean isCompId = competitionRepository.existsByCompId(compId);
        if (!isCompId) {
            return ResponseEntity.badRequest().body("공모전 게시물이 존재하지 않습니다.");
        }

        // SecurityConfig에서 ADMIN 권한만 put할 수 있도록 설정해서 필요없음
        // 작성한 사용자와 비교하여 Authentication의 권한 확인(ROLE_ADMIN)
        // Authentication authentication =
        // SecurityContextHolder.getContext().getAuthentication();

        // Collection<? extends GrantedAuthority> authorities =
        // authentication.getAuthorities();
        // Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        // GrantedAuthority auth = iterator.next();

        // String role = auth.getAuthority();
        // if (!role.equals("ADMIN")) {
        // System.out.println(">> role = USER");
        // return ResponseEntity.badRequest().body("관리자만 수정 가능합니다.");
        // }

        return new ResponseEntity<>(competitionService.competitionUpdate(compId, competition), HttpStatus.OK);
    }

    @DeleteMapping("/api/competition/{compId}")
    public ResponseEntity<?> delete(@PathVariable int compId) {
        return new ResponseEntity<>(competitionService.competitionDelete(compId), HttpStatus.OK);
    }

}

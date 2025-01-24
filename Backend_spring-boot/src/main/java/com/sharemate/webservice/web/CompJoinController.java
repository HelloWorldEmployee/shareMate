package com.sharemate.webservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharemate.webservice.domain.CompJoinEntity;
import com.sharemate.webservice.domain.CompJoinRepository;
import com.sharemate.webservice.service.CompJoinService;
import com.sharemate.webservice.service.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/compJoin") // 공통 경로 설정
@CrossOrigin // cors 회피
public class CompJoinController {

    @Autowired
    private final CompJoinService compJoinService;
    @Autowired
    private final CompJoinRepository compJoinRepository;

    private String authUserId() {
        // 현재 인증된 사용자 정보 가져오기
        // Authentication 객체를 사용하여 userId 추출
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String userId = customUserDetails.getUserId();

        return userId;
    }

    @PostMapping("/{compId}")
    public ResponseEntity<?> save(@PathVariable int compId) {

        // CompJoinEntity 객체 생성 및 설정
        CompJoinEntity compJoin = new CompJoinEntity();

        String userId = authUserId();

        int setCompId = compId;
        compJoin.setCompId(setCompId);
        compJoin.setUserId(userId);

        return new ResponseEntity<>(compJoinService.compJoinCreate(compJoin), HttpStatus.CREATED);
    }

    // compId, userId에 해당하는 댓글 1개 조회 -> POST userId 중복확인 및 DEL 삭제
    @GetMapping("/{compId}/{userId}")
    public ResponseEntity<?> findCompIdUserId(@PathVariable int compId, @PathVariable String userId) {

        CompJoinEntity compJoinData = compJoinRepository.findByCompIdAndUserId(compId, userId);
        if (compJoinData == null) {
            return ResponseEntity.badRequest().body("해당 공모전의 사용자 댓글이 없습니다.");
        }
        return new ResponseEntity<>(compJoinService.compIdUserIdSelectOne(compId, userId), HttpStatus.OK);
    }

    // compId에 해당하는 댓글 전체 조회
    @GetMapping("/{compId}")
    public ResponseEntity<?> findCompIdCompJoinEntity(@PathVariable int compId) {

        boolean isExist = compJoinRepository.existsByCompId(compId);
        if (!isExist) {
            return ResponseEntity.badRequest().body("해당 공모전의 댓글이 없습니다.");
        }

        return new ResponseEntity<>(compJoinService.compIdCompJoinSelect(compId), HttpStatus.OK);
    }

    // 모든 댓글 전체 조회는 사실상 필요 없음.
    // @GetMapping
    // public ResponseEntity<?> findAll() {
    //     return new ResponseEntity<>(compJoinService.compJoinSelect(), HttpStatus.OK);
    // }

    @DeleteMapping("/{compId}/{userId}")
    public ResponseEntity<?> delete(@PathVariable int compId, @PathVariable String userId) {
        System.out.println(">> CompJoinContorller delete : userId=" + userId);
        String authUserId = authUserId();
        System.out.println(">> authUserId = " + authUserId);

        if (!authUserId.equals(userId)) {
            return ResponseEntity.badRequest().body("댓글 작성자만 삭제할 수 있습니다.");
        }
        return new ResponseEntity<>(compJoinService.compJoinDelete(compId, userId), HttpStatus.OK);
    }
}

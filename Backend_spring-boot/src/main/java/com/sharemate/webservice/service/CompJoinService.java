package com.sharemate.webservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sharemate.webservice.domain.CompJoinEntity;
import com.sharemate.webservice.domain.CompJoinRepository;
import com.sharemate.webservice.domain.CompetitionEntity;
import com.sharemate.webservice.service.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CompJoinService {

    @Autowired
    private final CompJoinRepository compJoinRepository;

    @Transactional
    public CompJoinEntity compJoinCreate(CompJoinEntity compJoin) {

        return compJoinRepository.save(compJoin);
    }

    // compId, userId에 해당하는 댓글 1개 조회 -> POST userId 중복확인 및 DEL 삭제
    @Transactional
    public CompJoinEntity compIdUserIdSelectOne(int compId, String userId) {
        return compJoinRepository.findByCompIdAndUserId(compId, userId);
    }

    // compId에 해당하는 댓글 전체 조회
    @Transactional
    public List<CompJoinEntity> compIdCompJoinSelect(int compId) {

        return compJoinRepository.findByCompId(compId);
    }

    // 모든 댓글 전체 조회는 사실상 필요 없음.
    @Transactional
    public List<CompJoinEntity> compJoinSelect() {

        return compJoinRepository.findAll();
    }

    @Transactional
    public String compJoinDelete(int compId, String userId) {

        CompJoinEntity compJoinData = compJoinRepository.findByCompIdAndUserId(compId, userId);
        int joinId = compJoinData.getJoinId();
        System.out.println(">> CompJoinService compJoinDelete : joinId=" + joinId);

        compJoinRepository.deleteById(joinId);
        return "success compJoinDelete";
    }
}

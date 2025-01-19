package com.sharemate.webservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sharemate.webservice.domain.CompetitionEntity;
import com.sharemate.webservice.domain.CompetitionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final 생성자 자동생성
@Service
public class CompetitionService {

    @Autowired
    private final CompetitionRepository competitionRespository;

    // Authentication 객체를 사용하여 userId 추출
    private String authUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String userId = customUserDetails.getUserId();

        return userId;
    }

    @Transactional // 트랜잭션 관리
    public CompetitionEntity competitionCreate(CompetitionEntity competition) {

        String userId = authUserId();
        System.out.println(">> authUserId 메소드 : " + userId);

        competition.setUserId(userId);
        return competitionRespository.save(competition);
    }

    @Transactional
    public List<CompetitionEntity> allList() {
        return competitionRespository.findAll();
    }

    @Transactional
    public CompetitionEntity searchCompetition(int compId) {
        return competitionRespository.findByCompId(compId);
    }

    @Transactional
    public CompetitionEntity competitionUpdate(int compId, CompetitionEntity competition) {

        // compId에 해당하는 CompetitionEntity를 데이터베이스에서 조회
        CompetitionEntity compData = competitionRespository.findByCompId(compId);

        // 수정할 필드가 null이 아닐 경우 업데이트
        if (competition.getCompTitle() != null && !competition.getCompTitle().isEmpty()) {
            compData.setCompTitle(competition.getCompTitle());
        }
        if (competition.getComp_content() != null && !competition.getComp_content().isEmpty()) {
            compData.setComp_content(competition.getComp_content());
        }

        // 업데이트된 CompetitionEntity 반환
        return competitionRespository.save(compData);
    }

    @Transactional
    public String competitionDelete(int compId) {
        competitionRespository.deleteById(compId);
        return "success competitionDelete";
    }
}

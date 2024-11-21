package com.sharemate.webservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sharemate.webservice.domain.CompetitionEntity;
import com.sharemate.webservice.domain.CompetitionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final 생성자 자동생성
@Service
public class CompetitionService {

    private final CompetitionRepository competitionRespository;

    @Transactional // 트랜잭션 관리
    public CompetitionEntity competitionCreate(CompetitionEntity competition) {
        return competitionRespository.save(competition);
    }

    @Transactional(readOnly = true)
    public List<CompetitionEntity> allList() {
        return competitionRespository.findAll();
    }

    @Transactional
    public CompetitionEntity competitionUpdate(String user_id, CompetitionEntity competition) {
        CompetitionEntity compData = competitionRespository.findById(user_id).orElseThrow(()->new IllegalArgumentException(s:"작성한 'id'만 수정 가능합니다.")));
        compData.setComp_title(competition.getComp_title());
        compData.setComp_content(competition.getComp_content());

        return compData;
    }

    @Transactional
    private String competitionDelete(String user_id, int comp_id) {
        competitionRespository.deleteById(user_id);
        return "success";
    }
}

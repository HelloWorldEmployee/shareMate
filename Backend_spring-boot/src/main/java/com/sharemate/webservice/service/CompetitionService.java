package com.sharemate.webservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Transactional // 트랜잭션 관리
    public CompetitionEntity competitionCreate(CompetitionEntity competition) {
        return competitionRespository.save(competition);
    }

    @Transactional
    public List<CompetitionEntity> allList() {
        return competitionRespository.findAll();
    }

    @Transactional
    public CompetitionEntity competitionUpdate(String userId, CompetitionEntity competition) {
        // CompetitionEntity compData = competitionRespository.findByCompId(comp_id)
        // .orElseThrow(() -> new IllegalArgumentException("작성한 'id'만 수정 가능합니다."));
        List<CompetitionEntity> competitionList = new ArrayList<>();
        CompetitionEntity compData = competitionList.stream()
                .filter(c -> c.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("작성한 'id'만 수정 가능합니다."));
        compData.setComp_title(competition.getComp_title());
        compData.setComp_content(competition.getComp_content());

        return compData;
    }

    @Transactional
    public String competitionDelete(int compId) {
        competitionRespository.deleteById(compId);
        return "success competitionDelete";
    }
}

package com.sharemate.webservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sharemate.webservice.domain.CompJoinEntity;
import com.sharemate.webservice.domain.CompJoinRepository;

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

    @Transactional
    public List<CompJoinEntity> compJoinSelect() {
        return compJoinRepository.findAll();
    }

    @Transactional
    public String compJoinDelete(int compId, String userId) {
        compJoinRepository.findByJoinIdAndUserId(compId, userId);
        return "success compJoinDelete";
    }
}

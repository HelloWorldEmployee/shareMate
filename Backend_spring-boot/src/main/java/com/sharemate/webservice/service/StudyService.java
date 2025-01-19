package com.sharemate.webservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sharemate.webservice.domain.StudyEntity;
import com.sharemate.webservice.domain.StudyRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StudyService {

    private final StudyRepository studyRepository;

    @Transactional
    public StudyEntity studyCreate(StudyEntity study) {
        return studyRepository.save(study);
    }

    @Transactional(readOnly=true)
    public List<StudyEntity> allRead() {
        return studyRepository.findAll();
    }

    @Transactional
    public StudyEntity studyUpdate(int id, StudyEntity study) {
        StudyEntity studyData = studyRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("id를 확인해주세요!"));
            
        studyData.setStudy_name(study.getStudy_name());
        studyData.setStudy_content(study.getStudy_content());
        return studyData;
    }

    // @Transactional
    // public StudyEntity studyUpdateByUserId(String user_id, StudyEntity study) {
    //     StudyEntity studyData = studyRepository.findByUser_id(user_id)
    //         .orElseThrow(() -> new IllegalArgumentException("id를 확인해주세요!"));
            
    //     studyData.setStudy_name(study.getStudy_name());
    //     studyData.setStudy_content(study.getStudy_content());
    //     return studyData;
    // }

    @Transactional
    public String studyDelete(int id) {
        studyRepository.deleteById(id);
        return "success delete";
    }

    // @Transactional
    // public String studyDelete(int id, String user_id) {
    //     studyRepository.deleteByStudyIdAndUserId(id, user_id);
    //     return "success delete";
    // }
}

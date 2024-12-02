package com.sharemate.webservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sharemate.webservice.domain.StudyCommentEntity;
import com.sharemate.webservice.domain.StudyCommentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StudyCommentService {
    private final StudyCommentRepository studyCommentRepository;

    @Transactional
    public StudyCommentEntity studyCommentCreate(StudyCommentEntity studyComment) {
        return studyCommentRepository.save(studyComment);
    }

    @Transactional(readOnly=true)
    public List<StudyCommentEntity> allRead() {
        return studyCommentRepository.findAll();
    }

    @Transactional
    public StudyCommentEntity studyCommentUpdate(int id, StudyCommentEntity studyComment) {
        StudyCommentEntity studyCommentData = studyCommentRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("id를 확인해주세요!"));
        
        studyCommentData.setStudy_comment_content(studyComment.getStudy_comment_content());
        return studyCommentData;
    }

    @Transactional
    public String studyCommentDelete(int studyCommentId, String userId) {
        studyCommentRepository.deleteByStudyCommentIdAndUserId(studyCommentId, userId);
        return "success studyComment";
    }

}

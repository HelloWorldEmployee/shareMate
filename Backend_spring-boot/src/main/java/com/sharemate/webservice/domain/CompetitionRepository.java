package com.sharemate.webservice.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<CompetitionEntity, Integer> {

    List<CompetitionEntity> findByCompId(int compId);

}

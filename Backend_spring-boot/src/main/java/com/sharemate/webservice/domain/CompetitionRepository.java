package com.sharemate.webservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<CompetitionEntity, Integer> {

    Boolean existsByCompId(int compId);

    CompetitionEntity findByCompId(int compId);
}

package com.upc.ViksAdventures.first_world.domain.persistence;

import com.upc.ViksAdventures.first_world.domain.model.Formula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormulaRepository extends JpaRepository<Formula, Long> {
    List<Formula> findFormulasByTopicId(Long topicId);
}

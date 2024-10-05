package com.upc.ViksAdventures.first_world.domain.service;

import com.upc.ViksAdventures.first_world.domain.model.Formula;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FormulaService {
    List<Formula> getAll();
    List<Formula> getByTopicId(Long topicId);
    Formula getById(Long formulaId);
    Formula create(Formula formula);
    Formula update(Long formulaId, Formula formula);
    ResponseEntity<?> delete(Long formulaId);
}

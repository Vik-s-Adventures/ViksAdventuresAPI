package com.upc.ViksAdventures.first_world.domain.service;

import com.upc.ViksAdventures.first_world.domain.model.Competence;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompetenceService {
    List<Competence> getAll();
    Competence getById(Long competenceId);
    Competence create(Competence competence);
    Competence update(Long competenceId, Competence competence);
    ResponseEntity<?> delete(Long competenceId);
}

package com.upc.ViksAdventures.first_world.service;

import com.upc.ViksAdventures.first_world.domain.model.Competence;
import com.upc.ViksAdventures.first_world.domain.persistence.CompetenceRepository;
import com.upc.ViksAdventures.first_world.domain.service.CompetenceService;
import com.upc.ViksAdventures.shared.exception.ResourceNotFoundException;
import com.upc.ViksAdventures.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CompetenceServiceImpl implements CompetenceService {

    private static final String ENTITY = "Competence";
    private final CompetenceRepository competenceRepository;
    private final Validator validator;

    public CompetenceServiceImpl(CompetenceRepository competenceRepository, Validator validator) {
        this.competenceRepository = competenceRepository;
        this.validator = validator;
    }

    @Override
    public List<Competence> getAll() {
        return competenceRepository.findAll();
    }

    @Override
    public Competence getById(Long competenceId) {
        return competenceRepository.findById(competenceId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, competenceId));
    }

    @Override
    public Competence create(Competence competence) {
        Set<ConstraintViolation<Competence>> violations = validator.validate(competence);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }
        return competenceRepository.save(competence);
    }

    @Override
    public Competence update(Long competenceId, Competence competence) {
        Competence existingCompetence = competenceRepository.findById(competenceId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, competenceId));

        existingCompetence.setTitle(competence.getTitle());
        existingCompetence.setDescription(competence.getDescription());

        Set<ConstraintViolation<Competence>> violations = validator.validate(existingCompetence);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        return competenceRepository.save(existingCompetence);
    }

    @Override
    public ResponseEntity<?> delete(Long competenceId) {
        return competenceRepository.findById(competenceId).map(competence -> {
            competenceRepository.delete(competence);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, competenceId));
    }
}

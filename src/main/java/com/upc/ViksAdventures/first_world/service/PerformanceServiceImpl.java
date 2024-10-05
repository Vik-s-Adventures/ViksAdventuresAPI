package com.upc.ViksAdventures.first_world.service;

import com.upc.ViksAdventures.first_world.domain.model.Performance;
import com.upc.ViksAdventures.first_world.domain.persistence.PerformanceRepository;
import com.upc.ViksAdventures.first_world.domain.service.PerformanceService;
import com.upc.ViksAdventures.shared.exception.ResourceNotFoundException;
import com.upc.ViksAdventures.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PerformanceServiceImpl implements PerformanceService {

    private static final String ENTITY = "Performance";
    private final PerformanceRepository performanceRepository;
    private final Validator validator;

    public PerformanceServiceImpl(PerformanceRepository performanceRepository, Validator validator) {
        this.performanceRepository = performanceRepository;
        this.validator = validator;
    }

    @Override
    public List<Performance> getAll() {
        return performanceRepository.findAll();
    }

    @Override
    public List<Performance> getByCompetenceId(Long competenceId) {
        return performanceRepository.findPerformancesByCompetenceId(competenceId);
    }

    @Override
    public Performance getById(Long performanceId) {
        return performanceRepository.findById(performanceId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, performanceId));
    }

    @Override
    public Performance create(Performance performance) {
        Set<ConstraintViolation<Performance>> violations = validator.validate(performance);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }
        return performanceRepository.save(performance);
    }

    @Override
    public Performance update(Long performanceId, Performance performance) {
        Performance existingPerformance = performanceRepository.findById(performanceId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, performanceId));

        existingPerformance.setDescription(performance.getDescription());
        existingPerformance.setCompetence(performance.getCompetence());

        Set<ConstraintViolation<Performance>> violations = validator.validate(existingPerformance);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        return performanceRepository.save(existingPerformance);
    }

    @Override
    public ResponseEntity<?> delete(Long performanceId) {
        return performanceRepository.findById(performanceId).map(performance -> {
            performanceRepository.delete(performance);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, performanceId));
    }
}

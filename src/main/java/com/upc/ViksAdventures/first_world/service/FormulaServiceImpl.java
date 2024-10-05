package com.upc.ViksAdventures.first_world.service;

import com.upc.ViksAdventures.first_world.domain.model.Formula;
import com.upc.ViksAdventures.first_world.domain.persistence.FormulaRepository;
import com.upc.ViksAdventures.first_world.domain.service.FormulaService;
import com.upc.ViksAdventures.shared.exception.ResourceNotFoundException;
import com.upc.ViksAdventures.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class FormulaServiceImpl implements FormulaService {

    private static final String ENTITY = "Formula";
    private final FormulaRepository formulaRepository;
    private final Validator validator;

    public FormulaServiceImpl(FormulaRepository formulaRepository, Validator validator) {
        this.formulaRepository = formulaRepository;
        this.validator = validator;
    }

    @Override
    public List<Formula> getAll() {
        return formulaRepository.findAll();
    }

    @Override
    public List<Formula> getByTopicId(Long topicId) {
        return formulaRepository.findFormulasByTopicId(topicId);
    }

    @Override
    public Formula getById(Long formulaId) {
        return formulaRepository.findById(formulaId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, formulaId));
    }

    @Override
    public Formula create(Formula formula) {
        Set<ConstraintViolation<Formula>> violations = validator.validate(formula);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }
        return formulaRepository.save(formula);
    }

    @Override
    public Formula update(Long formulaId, Formula formula) {
        Formula existingFormula = formulaRepository.findById(formulaId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, formulaId));

        existingFormula.setExpression(formula.getExpression());
        existingFormula.setTopic(formula.getTopic());

        Set<ConstraintViolation<Formula>> violations = validator.validate(existingFormula);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        return formulaRepository.save(existingFormula);
    }

    @Override
    public ResponseEntity<?> delete(Long formulaId) {
        return formulaRepository.findById(formulaId).map(formula -> {
            formulaRepository.delete(formula);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, formulaId));
    }
}

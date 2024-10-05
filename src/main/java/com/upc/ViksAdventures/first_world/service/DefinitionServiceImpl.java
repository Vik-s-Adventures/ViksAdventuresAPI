package com.upc.ViksAdventures.first_world.service;

import com.upc.ViksAdventures.first_world.domain.model.Definition;
import com.upc.ViksAdventures.first_world.domain.persistence.DefinitionRepository;
import com.upc.ViksAdventures.first_world.domain.service.DefinitionService;
import com.upc.ViksAdventures.shared.exception.ResourceNotFoundException;
import com.upc.ViksAdventures.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DefinitionServiceImpl implements DefinitionService {

    private static final String ENTITY = "Definition";
    private final DefinitionRepository definitionRepository;
    private final Validator validator;

    public DefinitionServiceImpl(DefinitionRepository definitionRepository, Validator validator) {
        this.definitionRepository = definitionRepository;
        this.validator = validator;
    }

    @Override
    public List<Definition> getAll() {
        return definitionRepository.findAll();
    }

    @Override
    public List<Definition> getByTopicId(Long topicId) {
        return definitionRepository.findDefinitionsByTopicId(topicId);
    }

    @Override
    public Definition getById(Long definitionId) {
        return definitionRepository.findById(definitionId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, definitionId));
    }

    @Override
    public Definition create(Definition definition) {
        Set<ConstraintViolation<Definition>> violations = validator.validate(definition);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }
        return definitionRepository.save(definition);
    }

    @Override
    public Definition update(Long definitionId, Definition definition) {
        Definition existingDefinition = definitionRepository.findById(definitionId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, definitionId));

        existingDefinition.setDescription(definition.getDescription());
        existingDefinition.setTopic(definition.getTopic());

        Set<ConstraintViolation<Definition>> violations = validator.validate(existingDefinition);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        return definitionRepository.save(existingDefinition);
    }

    @Override
    public ResponseEntity<?> delete(Long definitionId) {
        return definitionRepository.findById(definitionId).map(definition -> {
            definitionRepository.delete(definition);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, definitionId));
    }
}

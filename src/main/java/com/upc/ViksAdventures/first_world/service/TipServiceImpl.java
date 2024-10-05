package com.upc.ViksAdventures.first_world.service;

import com.upc.ViksAdventures.first_world.domain.model.Tip;
import com.upc.ViksAdventures.first_world.domain.persistence.TipRepository;
import com.upc.ViksAdventures.first_world.domain.service.TipService;
import com.upc.ViksAdventures.shared.exception.ResourceNotFoundException;
import com.upc.ViksAdventures.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TipServiceImpl implements TipService {

    private static final String ENTITY = "Tip";
    private final TipRepository tipRepository;
    private final Validator validator;

    public TipServiceImpl(TipRepository tipRepository, Validator validator) {
        this.tipRepository = tipRepository;
        this.validator = validator;
    }

    @Override
    public List<Tip> getAll() {
        return tipRepository.findAll();
    }

    @Override
    public List<Tip> getByTopicId(Long topicId) {
        return tipRepository.findTipsByTopicId(topicId);
    }

    @Override
    public Tip getById(Long tipId) {
        return tipRepository.findById(tipId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, tipId));
    }

    @Override
    public Tip create(Tip tip) {
        Set<ConstraintViolation<Tip>> violations = validator.validate(tip);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }
        return tipRepository.save(tip);
    }

    @Override
    public Tip update(Long tipId, Tip tip) {
        Tip existingTip = tipRepository.findById(tipId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, tipId));

        existingTip.setTipText(tip.getTipText());
        existingTip.setTopic(tip.getTopic());

        Set<ConstraintViolation<Tip>> violations = validator.validate(existingTip);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        return tipRepository.save(existingTip);
    }

    @Override
    public ResponseEntity<?> delete(Long tipId) {
        return tipRepository.findById(tipId).map(tip -> {
            tipRepository.delete(tip);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, tipId));
    }
}

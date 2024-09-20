package com.upc.ViksAdventures.quiz.service;

import com.upc.ViksAdventures.quiz.domain.model.AnswerOption;
import com.upc.ViksAdventures.quiz.domain.persistence.AnswerOptionRepository;
import com.upc.ViksAdventures.quiz.domain.service.AnswerOptionService;
import com.upc.ViksAdventures.shared.exception.ResourceNotFoundException;
import com.upc.ViksAdventures.shared.exception.ResourceValidationException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AnswerOptionServiceImpl implements AnswerOptionService {
    private static final String ENTITY = "AnswerOption";

    private final AnswerOptionRepository answerOptionRepository;
    private final Validator validator;

    public AnswerOptionServiceImpl(AnswerOptionRepository answerOptionRepository, Validator validator) {
        this.answerOptionRepository = answerOptionRepository;
        this.validator = validator;
    }

    @Override
    public List<AnswerOption> getAll() {
        return answerOptionRepository.findAll();
    }

    @Override
    public AnswerOption getBydId(Long answerOptionId) {
        return answerOptionRepository.findById(answerOptionId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, answerOptionId));
    }

    @Transactional
    @Override
    public AnswerOption create(AnswerOption answerOption) {
        Set<ConstraintViolation<AnswerOption>> violations = validator.validate(answerOption);

        // Si hay violaciones, lanzar una excepción de validación
        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }
        return answerOptionRepository.save(answerOption);
    }

    @Transactional
    @Override
    public AnswerOption update(Long id, AnswerOption answerOption) {
        Set<ConstraintViolation<AnswerOption>> violations = validator.validate(answerOption);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        return answerOptionRepository.findById(id).map(existingOption -> {
            // Validar si ya existe una opción correcta
            validateCorrectAnswerOption(answerOption);

            existingOption.setAnswerText(answerOption.getAnswerText());
            existingOption.setCorrect(answerOption.isCorrect());
            return answerOptionRepository.save(existingOption);
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }


    @Override
    public ResponseEntity<?> delete(Long answerOptionId) {
        return answerOptionRepository.findById(answerOptionId).map(option -> {
            answerOptionRepository.delete(option);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, answerOptionId));
    }

    private void validateCorrectAnswerOption(AnswerOption answerOption) {
        if (answerOption.isCorrect()) {
            int correctAnswersCount = answerOptionRepository
                    .countAnswerOptionByQuestionIdAndCorrect(answerOption.getQuestion().getId(), true);

            if (correctAnswersCount > 0) {
                throw new ResourceValidationException(ENTITY, "Solo debe haber una respuesta correcta por pregunta.");
            }
        }
    }
}

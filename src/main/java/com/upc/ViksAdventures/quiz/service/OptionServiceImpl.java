package com.upc.ViksAdventures.quiz.service;

import com.upc.ViksAdventures.quiz.domain.model.Option;
import com.upc.ViksAdventures.quiz.domain.persistence.OptionRepository;
import com.upc.ViksAdventures.quiz.domain.service.OptionService;
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
public class OptionServiceImpl implements OptionService {
    private static final String ENTITY = "Option";
    private final OptionRepository optionRepository;
    private final Validator validator;

    public OptionServiceImpl(OptionRepository optionRepository, Validator validator) {
        this.optionRepository = optionRepository;
        this.validator = validator;
    }

    @Override
    public List<Option> getAll() {
        return optionRepository.findAll();
    }

    @Override
    public Option getBydId(Long answerOptionId) {
        return optionRepository.findById(answerOptionId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, answerOptionId));
    }

    @Transactional
    @Override
    public Option create(Option option) {
        Set<ConstraintViolation<Option>> violations = validator.validate(option);
        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }
        // Validar si ya existe una opción correcta
        validateCorrectAnswerOption(option);
        return optionRepository.save(option);
    }

    @Transactional
    @Override
    public Option update(Long id, Option option) {
        Set<ConstraintViolation<Option>> violations = validator.validate(option);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }
        return optionRepository.findById(id).map(existingOption -> {
            // Validar si ya existe una opción correcta
            validateCorrectAnswerOption(option);
            existingOption.setCorrect(option.isCorrect());
            return optionRepository.save(existingOption);
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }


    @Override
    public ResponseEntity<?> delete(Long answerOptionId) {
        return optionRepository.findById(answerOptionId).map(option -> {
            optionRepository.delete(option);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, answerOptionId));
    }

    private void validateCorrectAnswerOption(Option option) {
        if (option.isCorrect()) {
            int correctAnswersCount = optionRepository
                    .countOptionByQuestionIdAndCorrect(option.getQuestion().getId(), true);

            if (correctAnswersCount > 0) {
                throw new ResourceValidationException(ENTITY, "Solo debe haber una respuesta correcta por pregunta.");
            }
        }
    }
}

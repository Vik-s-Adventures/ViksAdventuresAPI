package com.upc.ViksAdventures.quiz.service;

import com.upc.ViksAdventures.quiz.domain.model.Option;
import com.upc.ViksAdventures.quiz.domain.persistence.OptionRepository;
import com.upc.ViksAdventures.quiz.domain.service.OptionService;
import com.upc.ViksAdventures.shared.exception.ResourceNotFoundException;
import com.upc.ViksAdventures.shared.exception.ResourceValidationException;
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

    @Override
    public Option create(Option option) {
        // Validar las restricciones
        Set<ConstraintViolation<Option>> violations = validator.validate(option);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        validateCorrectAnswerOption(option);
        return optionRepository.save(option);
    }


    @Override
    public Option update(Long id, Option option) {
        // Verificar si la opción existe
        Option existingOption = optionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la opción con el id: " + id));

        // Actualizar los campos
        existingOption.setText(option.getText());
        existingOption.setCorrect(option.isCorrect());
        existingOption.setQuestion(option.getQuestion());

        // Validar la entidad actualizada
        Set<ConstraintViolation<Option>> violations = validator.validate(existingOption);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException("Option", violations);
        }
        validateCorrectAnswerOption(existingOption);
        return optionRepository.save(existingOption);
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
            Long questionId = option.getQuestion().getId();

            // Contar opciones correctas
            int correctAnswersCount = optionRepository
                    .countOptionByQuestionIdAndCorrect(questionId, true);

            // Si hay una opcion correcta ya registrada, lanzar excepción
            if (correctAnswersCount > 0) {
                throw new ResourceValidationException(ENTITY, "Solo puede haber una opción correcta por pregunta.");
            }
        }
    }

}

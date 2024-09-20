package com.upc.ViksAdventures.quiz.service;

import com.upc.ViksAdventures.quiz.domain.model.QuizResult;
import com.upc.ViksAdventures.quiz.domain.persistence.QuizResultRepository;
import com.upc.ViksAdventures.quiz.domain.service.QuizResultService;
import com.upc.ViksAdventures.shared.exception.ResourceNotFoundException;
import com.upc.ViksAdventures.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class QuizResultServiceImpl implements QuizResultService {
    private static final String ENTITY = "QuizResult";
    private final QuizResultRepository quizResultRepository;
    private final Validator validator;

    public QuizResultServiceImpl(QuizResultRepository quizResultRepository, Validator validator) {
        this.quizResultRepository = quizResultRepository;
        this.validator = validator;
    }

    @Override
    public List<QuizResult> getAll() {
        return quizResultRepository.findAll();
    }

    @Override
    public QuizResult getBydId(Long quizResultId) {
        return quizResultRepository.findById(quizResultId).orElse(null);
    }

    @Override
    public QuizResult create(QuizResult quizResult) {
        // Validación de restricciones
        Set<ConstraintViolation<QuizResult>> violations = validator.validate(quizResult);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        // Realiza cualquier lógica adicional si es necesario
        return quizResultRepository.save(quizResult);
    }

    @Override
    public ResponseEntity<?> delete(Long quizResultId) {
        return quizResultRepository.findById(quizResultId).map(quizResult -> {
            quizResultRepository.delete(quizResult);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, quizResultId));
    }
}

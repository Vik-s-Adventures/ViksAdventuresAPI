package com.upc.ViksAdventures.quiz.service;

import com.upc.ViksAdventures.quiz.domain.model.Quiz;
import com.upc.ViksAdventures.quiz.domain.persistence.QuizRepository;
import com.upc.ViksAdventures.quiz.domain.service.QuizService;
import com.upc.ViksAdventures.shared.exception.ResourceNotFoundException;
import com.upc.ViksAdventures.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class QuizServiceImpl implements QuizService {
    private static final String ENTITY = "Quiz";

    private final QuizRepository quizRepository;
    private final Validator validator;

    public QuizServiceImpl(QuizRepository quizRepository, Validator validator) {
        this.quizRepository = quizRepository;
        this.validator = validator;
    }

    @Override
    public List<Quiz> getAll() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz getBydId(Long quizId) {
        return quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, quizId));
    }

    @Override
    public Quiz create(Quiz quiz) {
        // Validación de restricciones
        Set<ConstraintViolation<Quiz>> violations = validator.validate(quiz);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        // Guardar el quiz en la base de datos
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz update(Long id, Quiz quiz) {
        // Validación de restricciones
        Set<ConstraintViolation<Quiz>> violations = validator.validate(quiz);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        return quizRepository.findById(id).map(existingQuiz -> {
            existingQuiz.setTitle(quiz.getTitle());
            existingQuiz.setDescription(quiz.getDescription());
            existingQuiz.setQuestions(quiz.getQuestions());
            return quizRepository.save(existingQuiz);
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public ResponseEntity<?> delete(Long quizId) {
        return quizRepository.findById(quizId).map(quiz -> {
            quizRepository.delete(quiz);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, quizId));
    }
}

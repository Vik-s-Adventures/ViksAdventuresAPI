package com.upc.ViksAdventures.quiz.service;

import com.upc.ViksAdventures.quiz.domain.model.Question;
import com.upc.ViksAdventures.quiz.domain.persistence.QuestionRepository;
import com.upc.ViksAdventures.quiz.domain.service.QuestionService;
import com.upc.ViksAdventures.shared.exception.ResourceNotFoundException;
import com.upc.ViksAdventures.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService {

    private static final String ENTITY = "Question";
    private final QuestionRepository questionRepository;
    private final Validator validator;

    public QuestionServiceImpl(QuestionRepository questionRepository, Validator validator) {
        this.questionRepository = questionRepository;
        this.validator = validator;
    }

    @Override
    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    @Override
    public List<Question> getQuestionsByQuizId(Long quizId) {
        return questionRepository.findQuestionsByQuizId(quizId);
    }

    @Override
    public Question getBydId(Long questionId) {
        return questionRepository.findById(questionId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, questionId));
    }

    @Override
    public Question create(Question question) {
        // Validar las restricciones
        Set<ConstraintViolation<Question>> violations = validator.validate(question);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        // Guardar la pregunta en la base de datos
        return questionRepository.save(question);
    }

    @Override
    public Question update(Long id, Question question) {
        // Validar las restricciones del objeto Question
        Set<ConstraintViolation<Question>> violations = validator.validate(question);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }
        return questionRepository.findById(id).map(existingQuestion -> {
            existingQuestion.setQuestionText(question.getQuestionText());
            existingQuestion.setPerformance(question.getPerformance());
            return questionRepository.save(existingQuestion);
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public ResponseEntity<?> delete(Long questionId) {
        return questionRepository.findById(questionId).map(question -> {
            questionRepository.delete(question);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, questionId));
    }
}

package com.upc.ViksAdventures.quiz.service;

import com.upc.ViksAdventures.quiz.domain.model.Result;
import com.upc.ViksAdventures.quiz.domain.persistence.ResultRepository;
import com.upc.ViksAdventures.quiz.domain.service.ResultService;
import com.upc.ViksAdventures.shared.exception.ResourceNotFoundException;
import com.upc.ViksAdventures.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ResultServiceImpl implements ResultService {
    private static final String ENTITY = "Result";
    private final ResultRepository resultRepository;
    private final Validator validator;

    public ResultServiceImpl(ResultRepository resultRepository, Validator validator) {
        this.resultRepository = resultRepository;
        this.validator = validator;
    }

    @Override
    public List<Result> getAll() {
        return resultRepository.findAll();
    }

    @Override
    public Result getBydId(Long quizResultId) {
        return resultRepository.findById(quizResultId).orElse(null);
    }

    @Override
    public Result create(Result result) {
        // Validación de restricciones
        Set<ConstraintViolation<Result>> violations = validator.validate(result);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        // Realiza cualquier lógica adicional si es necesario
        return resultRepository.save(result);
    }

    @Override
    public ResponseEntity<?> delete(Long resultId) {
        return resultRepository.findById(resultId).map(quizResult -> {
            resultRepository.delete(quizResult);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, resultId));
    }
}

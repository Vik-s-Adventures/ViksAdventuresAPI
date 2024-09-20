package com.upc.ViksAdventures.quiz.service;

import com.upc.ViksAdventures.quiz.domain.model.StudentResponse;
import com.upc.ViksAdventures.quiz.domain.persistence.StudentResponseRepository;
import com.upc.ViksAdventures.quiz.domain.service.StudentResponseService;
import com.upc.ViksAdventures.shared.exception.ResourceNotFoundException;
import com.upc.ViksAdventures.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StudentResponseServiceImpl implements StudentResponseService {
    private static final String ENTITY = "StudentResponse";

    private final StudentResponseRepository studentResponseRepository;
    private final Validator validator;

    public StudentResponseServiceImpl(StudentResponseRepository studentResponseRepository, Validator validator) {
        this.studentResponseRepository = studentResponseRepository;
        this.validator = validator;
    }

    @Override
    public List<StudentResponse> getAll() {
        return studentResponseRepository.findAll();
    }

    @Override
    public StudentResponse getBydId(Long studentResponseId) {
        return studentResponseRepository.findById(studentResponseId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, studentResponseId));
    }

    @Override
    public StudentResponse create(StudentResponse studentResponse) {
        Set<ConstraintViolation<StudentResponse>> violations = validator.validate(studentResponse);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        return studentResponseRepository.save(studentResponse);
    }

    @Override
    public ResponseEntity<?> delete(Long studentResponseId) {
        return studentResponseRepository.findById(studentResponseId).map(response -> {
            studentResponseRepository.delete(response);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, studentResponseId));
    }
}

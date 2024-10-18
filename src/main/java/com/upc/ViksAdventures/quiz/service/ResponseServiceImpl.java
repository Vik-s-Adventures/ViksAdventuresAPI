package com.upc.ViksAdventures.quiz.service;

import com.upc.ViksAdventures.quiz.domain.events.ResponseCreatedEvent;
import com.upc.ViksAdventures.quiz.domain.model.Quiz;
import com.upc.ViksAdventures.quiz.domain.model.Response;
import com.upc.ViksAdventures.quiz.domain.model.Student;
import com.upc.ViksAdventures.quiz.domain.persistence.ResponseRepository;
import com.upc.ViksAdventures.quiz.domain.service.ResponseService;
import com.upc.ViksAdventures.shared.exception.ResourceNotFoundException;
import com.upc.ViksAdventures.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ResponseServiceImpl implements ResponseService {
    private static final String ENTITY = "Response";

    private final ResponseRepository responseRepository;
    private final Validator validator;
    private final ApplicationEventPublisher eventPublisher;

    public ResponseServiceImpl(ResponseRepository responseRepository, Validator validator, ApplicationEventPublisher eventPublisher) {
        this.responseRepository = responseRepository;
        this.validator = validator;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public List<Response> getAll() {
        return responseRepository.findAll();
    }

    @Override
    public Response getBydId(Long studentResponseId) {
        return responseRepository.findById(studentResponseId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, studentResponseId));
    }

    @Override
    public List<Response> getResponsesByStudentAndQuiz(Student student, Quiz quiz) {
        return responseRepository.findByStudentAndQuiz(student, quiz);
    }

    @Override
    public Response create(Response response) {
        Set<ConstraintViolation<Response>> violations = validator.validate(response);
        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }
        Response savedResponse = responseRepository.save(response);
        // Dispara el evento
        eventPublisher.publishEvent(new ResponseCreatedEvent(this, savedResponse));
        return savedResponse;
    }

    @Override
    public ResponseEntity<?> delete(Long studentResponseId) {
        return responseRepository.findById(studentResponseId).map(response -> {
            responseRepository.delete(response);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, studentResponseId));
    }
}

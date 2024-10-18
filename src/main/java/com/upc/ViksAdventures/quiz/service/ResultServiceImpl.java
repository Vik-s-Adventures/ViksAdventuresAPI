package com.upc.ViksAdventures.quiz.service;

import com.upc.ViksAdventures.quiz.domain.model.Quiz;
import com.upc.ViksAdventures.quiz.domain.model.Response;
import com.upc.ViksAdventures.quiz.domain.model.Result;
import com.upc.ViksAdventures.quiz.domain.model.Student;
import com.upc.ViksAdventures.quiz.domain.persistence.ResponseRepository;
import com.upc.ViksAdventures.quiz.domain.persistence.ResultRepository;
import com.upc.ViksAdventures.quiz.domain.service.ResultService;
import com.upc.ViksAdventures.shared.exception.ResourceNotFoundException;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {
    private static final String ENTITY = "Result";
    private final ResultRepository resultRepository;
    private final ResponseRepository responseRepository;
    private final Validator validator;

    public ResultServiceImpl(ResultRepository resultRepository, ResponseRepository responseRepository, Validator validator) {
        this.resultRepository = resultRepository;
        this.responseRepository = responseRepository;
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
    public void updateOrCreateResult(Student student, Quiz quiz) {
        // Buscar si ya existe un resultado para este estudiante y este quiz
        Result result = resultRepository.findByStudentAndQuiz(student, quiz);

        // Si no existe, crear un nuevo resultado
        if (result == null) {
            result = new Result();
            result.setStudent(student);
            result.setScore(0);
            result.setQuizId(quiz.getId());
        }

        // Obtener todas las respuestas del estudiante para este quiz
        List<Response> responses = responseRepository.findByStudentAndQuiz(student, quiz);

        // Recalcular el puntaje
        int newScore = (int) responses.stream()
                .filter(response -> response.getOption().isCorrect())
                .count();
        result.setScore(newScore);

        // Guardar
        resultRepository.save(result);
    }

    @Override
    public ResponseEntity<?> delete(Long resultId) {
        return resultRepository.findById(resultId).map(quizResult -> {
            resultRepository.delete(quizResult);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, resultId));
    }
}

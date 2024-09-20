package com.upc.ViksAdventures.quiz.api.rest;

import com.upc.ViksAdventures.quiz.domain.model.QuizResult;
import com.upc.ViksAdventures.quiz.domain.service.QuizResultService;
import com.upc.ViksAdventures.quiz.mapping.QuizResultMapper;
import com.upc.ViksAdventures.quiz.resource.CreateQuizResultResource;
import com.upc.ViksAdventures.quiz.resource.QuizResultResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/quiz-results", produces = "application/json")
public class QuizResultController {
    private final QuizResultService quizResultService;
    private final QuizResultMapper mapper;

    public QuizResultController(QuizResultService quizResultService, QuizResultMapper mapper) {
        this.quizResultService = quizResultService;
        this.mapper = mapper;
    }

    // Obtener todos los resultados de quizzes
    @GetMapping
    public List<QuizResultResource> getAllQuizResults() {
        return mapper.toResourceList(quizResultService.getAll());
    }

    // Obtener un resultado de quiz por su id
    @GetMapping("/{id}")
    public QuizResultResource getQuizResultById(@PathVariable Long id) {
        return mapper.toResource(quizResultService.getBydId(id));
    }

    // Crear un nuevo resultado de quiz
    @PostMapping
    public QuizResultResource createQuizResult(@RequestBody CreateQuizResultResource resource) {
        QuizResult quizResult = mapper.toModel(resource);
        return mapper.toResource(quizResultService.create(quizResult));
    }

    // Eliminar un resultado de quiz
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuizResult(@PathVariable Long id) {
        return quizResultService.delete(id);
    }

}

package com.upc.ViksAdventures.quiz.api.rest;

import com.upc.ViksAdventures.quiz.domain.model.Result;
import com.upc.ViksAdventures.quiz.domain.service.ResultService;
import com.upc.ViksAdventures.quiz.mapping.ResultMapper;
import com.upc.ViksAdventures.quiz.resource.CreateResultResource;
import com.upc.ViksAdventures.quiz.resource.ResultResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/quiz-results", produces = "application/json")
public class ResultController {
    private final ResultService quizResultService;
    private final ResultMapper mapper;

    @Autowired
    public ResultController(ResultService quizResultService, ResultMapper mapper) {
        this.quizResultService = quizResultService;
        this.mapper = mapper;
    }

    // Obtener todos los resultados de quizzes
    @GetMapping
    public List<ResultResource> getAllQuizResults() {
        return mapper.toResourceList(quizResultService.getAll());
    }

    // Obtener un resultado de quiz por su id
    @GetMapping("/{id}")
    public ResultResource getResultById(@PathVariable Long id) {
        return mapper.toResource(quizResultService.getBydId(id));
    }

    // Crear un nuevo resultado de quiz
    @PostMapping
    public ResultResource createQuizResult(@RequestBody CreateResultResource resource) {
        Result result = mapper.toModel(resource);
        return mapper.toResource(quizResultService.create(result));
    }

    // Eliminar un resultado de quiz
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuizResult(@PathVariable Long id) {
        return quizResultService.delete(id);
    }

}

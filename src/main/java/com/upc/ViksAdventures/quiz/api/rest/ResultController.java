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
@RequestMapping(value = "/api/v1/results", produces = "application/json")
public class ResultController {
    private final ResultService resultService;
    private final ResultMapper mapper;

    @Autowired
    public ResultController(ResultService resultService, ResultMapper mapper) {
        this.resultService = resultService;
        this.mapper = mapper;
    }

    // Obtener todos los resultados de quizzes
    @GetMapping
    public List<ResultResource> getAllQuizResults() {
        return mapper.toResourceList(resultService.getAll());
    }

    // Obtener un resultado de quiz por su id
    @GetMapping("/{id}")
    public ResultResource getResultById(@PathVariable Long id) {
        return mapper.toResource(resultService.getBydId(id));
    }

    // Eliminar un resultado de quiz
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuizResult(@PathVariable Long id) {
        return resultService.delete(id);
    }

}

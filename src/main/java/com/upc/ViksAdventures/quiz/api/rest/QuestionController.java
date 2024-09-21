package com.upc.ViksAdventures.quiz.api.rest;

import com.upc.ViksAdventures.quiz.domain.model.Performance;
import com.upc.ViksAdventures.quiz.domain.model.Question;
import com.upc.ViksAdventures.quiz.domain.service.QuestionService;
import com.upc.ViksAdventures.quiz.mapping.QuestionMapper;
import com.upc.ViksAdventures.quiz.resource.CreateQuestionResource;
import com.upc.ViksAdventures.quiz.resource.QuestionResource;
import com.upc.ViksAdventures.quiz.resource.UpdateQuestionResource;
import com.upc.ViksAdventures.shared.exception.ResourceNotFoundException;
import com.upc.ViksAdventures.shared.exception.ResourceValidationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/questions", produces = "application/json")
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper mapper;

    public QuestionController(QuestionService questionService, QuestionMapper mapper) {
        this.questionService = questionService;
        this.mapper = mapper;
    }

    // Obtener todas las preguntas
    @GetMapping
    public List<QuestionResource> getAllQuestions() {
        return mapper.toResourceList(questionService.getAll());
    }

    // Obtener preguntas por quizId
    @GetMapping("/quiz/{quizId}")
    public List<QuestionResource> getQuestionsByQuizId(@PathVariable Long quizId) {
        return mapper.toResourceList(questionService.getQuestionsByQuizId(quizId));
    }

    // Obtener una pregunta por su id
    @GetMapping("/{id}")
    public QuestionResource getQuestionById(@PathVariable Long id) {
        return questionService.getBydId(id)
                .map(mapper::toResource)
                .orElseThrow(() -> new ResourceNotFoundException("Question", id));
    }

    // Crear una nueva pregunta
    @PostMapping
    public QuestionResource createQuestion(@RequestBody CreateQuestionResource resource) {
        if (resource.getPerformance() < 1 || resource.getPerformance() > 10) {
            throw new ResourceValidationException("Question", "Invalid performance value: " + resource.getPerformance());
        }
        Question question = mapper.toModel(resource);
        return mapper.toResource(questionService.create(question));
    }

    // Actualizar una pregunta existente
    @PutMapping("/{id}")
    public QuestionResource updateQuestion(@PathVariable Long id, @RequestBody UpdateQuestionResource resource) {
        Question question = mapper.toModel(resource);
        return mapper.toResource(questionService.update(id, question));
    }

    // Eliminar una pregunta
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long id) {
        return questionService.delete(id);
    }

}

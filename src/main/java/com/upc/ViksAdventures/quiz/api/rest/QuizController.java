package com.upc.ViksAdventures.quiz.api.rest;

import com.upc.ViksAdventures.quiz.domain.model.Quiz;
import com.upc.ViksAdventures.quiz.domain.service.QuizService;
import com.upc.ViksAdventures.quiz.mapping.QuizMapper;
import com.upc.ViksAdventures.quiz.resource.CreateQuizResource;
import com.upc.ViksAdventures.quiz.resource.QuizResource;
import com.upc.ViksAdventures.quiz.resource.UpdateQuizResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/quizzes", produces = "application/json")
public class QuizController {
    private final QuizService quizService;
    private final QuizMapper mapper;

    public QuizController(QuizService quizService, QuizMapper mapper) {
        this.quizService = quizService;
        this.mapper = mapper;
    }
    // Obtener todos los quizzes
    @GetMapping
    public List<QuizResource> getAllQuizzes() {
        return mapper.toResourceList(quizService.getAll());
    }

    // Obtener un quiz por su id
    @GetMapping("/{id}")
    public QuizResource getQuizById(@PathVariable Long id) {
        return mapper.toResource(quizService.getBydId(id));
    }

    // Crear un nuevo quiz
    @PostMapping
    public QuizResource createQuiz(@RequestBody CreateQuizResource resource) {
        Quiz quiz = mapper.toModel(resource);
        return mapper.toResource(quizService.create(quiz));
    }

    // Actualizar un quiz existente
    @PutMapping("/{id}")
    public QuizResource updateQuiz(@PathVariable Long id, @RequestBody UpdateQuizResource resource) {
        Quiz quiz = mapper.toModel(resource);
        return mapper.toResource(quizService.update(id, quiz));
    }

    // Eliminar un quiz
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuiz(@PathVariable Long id) {
        return quizService.delete(id);
    }

}

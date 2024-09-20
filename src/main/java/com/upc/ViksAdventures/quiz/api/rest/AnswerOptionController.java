package com.upc.ViksAdventures.quiz.api.rest;

import com.upc.ViksAdventures.quiz.domain.model.AnswerOption;
import com.upc.ViksAdventures.quiz.domain.service.AnswerOptionService;
import com.upc.ViksAdventures.quiz.mapping.AnswerOptionMapper;
import com.upc.ViksAdventures.quiz.resource.AnswerOptionResource;
import com.upc.ViksAdventures.quiz.resource.CreateAnswerOptionResource;
import com.upc.ViksAdventures.quiz.resource.UpdateAnswerOptionResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/answer-options", produces = "application/json")
public class AnswerOptionController {
    private final AnswerOptionService answerOptionService;
    private final AnswerOptionMapper mapper;

    public AnswerOptionController(AnswerOptionService answerOptionService, AnswerOptionMapper mapper) {
        this.answerOptionService = answerOptionService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<AnswerOptionResource> getAllAnswerOptions() {
        return mapper.toResourceList(answerOptionService.getAll());
    }

    @GetMapping("/{id}")
    public AnswerOptionResource getAnswerOptionById(@PathVariable Long id) {
        return mapper.toResource(answerOptionService.getBydId(id));
    }

    @PostMapping
    public AnswerOptionResource createAnswerOption(@RequestBody CreateAnswerOptionResource resource) {
        AnswerOption answerOption = mapper.toModel(resource);
        return mapper.toResource(answerOptionService.create(answerOption));
    }

    @PutMapping("/{id}")
    public AnswerOptionResource updateAnswerOption(@PathVariable Long id, @RequestBody UpdateAnswerOptionResource resource) {
        AnswerOption answerOption = mapper.toModel(resource);
        return mapper.toResource(answerOptionService.update(id, answerOption));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnswerOption(@PathVariable Long id) {
        return answerOptionService.delete(id);
    }

}

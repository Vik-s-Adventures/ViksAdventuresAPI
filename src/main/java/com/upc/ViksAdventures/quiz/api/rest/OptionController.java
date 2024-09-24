package com.upc.ViksAdventures.quiz.api.rest;

import com.upc.ViksAdventures.quiz.domain.model.Option;
import com.upc.ViksAdventures.quiz.domain.model.Question;
import com.upc.ViksAdventures.quiz.domain.model.Quiz;
import com.upc.ViksAdventures.quiz.domain.service.OptionService;
import com.upc.ViksAdventures.quiz.domain.service.QuestionService;
import com.upc.ViksAdventures.quiz.mapping.OptionMapper;
import com.upc.ViksAdventures.quiz.resource.OptionResource;
import com.upc.ViksAdventures.quiz.resource.CreateOptionResource;
import com.upc.ViksAdventures.quiz.resource.UpdateOptionResource;
import com.upc.ViksAdventures.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/options", produces = "application/json")
public class OptionController {
    private final OptionService optionService;
    private final QuestionService questionService;
    private final OptionMapper mapper;

    @Autowired
    public OptionController(OptionService optionService, OptionMapper mapper, QuestionService questionService) {
        this.optionService = optionService;
        this.mapper = mapper;
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<List<OptionResource>> getAllOptions() {
        List<Option> options = optionService.getAll();
        List<OptionResource> resources = mapper.toResourceList(options);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OptionResource> getOptionById(@PathVariable Long id) {
        Option option = optionService.getBydId(id);
        OptionResource resource = mapper.toResource(option);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OptionResource> createOption(@RequestBody CreateOptionResource resource) {
        Question question = questionService.getBydId(resource.getQuestionId());

        Option option = new Option();
        option.setQuestion(question);
        option.setText(resource.getText());
        option.setCorrect(resource.isCorrect());

        Option savedOption = optionService.create(option);
        return new ResponseEntity<>(mapper.toResource(savedOption), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OptionResource> updateAnswerOption(@PathVariable Long id, @RequestBody UpdateOptionResource resource) {
        Question question = questionService.getBydId(resource.getQuestionId());
        Option option = mapper.toModel(resource);
        option.setQuestion(question);
        Option updatedOption = optionService.update(id, option);
        OptionResource optionResource = mapper.toResource(updatedOption);
        return new ResponseEntity<>(optionResource, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnswerOption(@PathVariable Long id) {
        optionService.delete(id);
        return ResponseEntity.ok().build();
    }
}

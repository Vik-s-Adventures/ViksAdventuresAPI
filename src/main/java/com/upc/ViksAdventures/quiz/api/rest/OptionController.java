package com.upc.ViksAdventures.quiz.api.rest;

import com.upc.ViksAdventures.quiz.domain.model.Option;
import com.upc.ViksAdventures.quiz.domain.service.OptionService;
import com.upc.ViksAdventures.quiz.mapping.OptionMapper;
import com.upc.ViksAdventures.quiz.resource.OptionResource;
import com.upc.ViksAdventures.quiz.resource.CreateOptionResource;
import com.upc.ViksAdventures.quiz.resource.UpdateOptionResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/options", produces = "application/json")
public class OptionController {
    private final OptionService optionService;
    private final OptionMapper mapper;

    public OptionController(OptionService optionService, OptionMapper mapper) {
        this.optionService = optionService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<OptionResource> getAllAnswerOptions() {
        return mapper.toResourceList(optionService.getAll());
    }

    @GetMapping("/{id}")
    public OptionResource getAnswerOptionById(@PathVariable Long id) {
        return mapper.toResource(optionService.getBydId(id));
    }

    @PostMapping
    public OptionResource createAnswerOption(@RequestBody CreateOptionResource resource) {
        Option option = mapper.toModel(resource);
        return mapper.toResource(optionService.create(option));
    }

    @PutMapping("/{id}")
    public OptionResource updateAnswerOption(@PathVariable Long id, @RequestBody UpdateOptionResource resource) {
        Option option = mapper.toModel(resource);
        return mapper.toResource(optionService.update(id, option));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnswerOption(@PathVariable Long id) {
        return optionService.delete(id);
    }

}

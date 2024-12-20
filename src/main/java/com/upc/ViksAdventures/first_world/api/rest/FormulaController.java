package com.upc.ViksAdventures.first_world.api.rest;

import com.upc.ViksAdventures.first_world.domain.model.Formula;
import com.upc.ViksAdventures.first_world.domain.model.Topic;
import com.upc.ViksAdventures.first_world.domain.service.FormulaService;
import com.upc.ViksAdventures.first_world.domain.service.TopicService;
import com.upc.ViksAdventures.first_world.mapping.FormulaMapper;
import com.upc.ViksAdventures.first_world.resource.FormulaResource;
import com.upc.ViksAdventures.first_world.resource.CreateFormulaResource;
import com.upc.ViksAdventures.first_world.resource.UpdateFormulaResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/formulas", produces = "application/json")
public class FormulaController {
    private final TopicService topicService;
    private final FormulaService formulaService;
    private final FormulaMapper mapper;

    @Autowired
    public FormulaController(TopicService topicService, FormulaService formulaService, FormulaMapper mapper) {
        this.topicService = topicService;
        this.formulaService = formulaService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<FormulaResource>> getAllFormulas() {
        List<Formula> formulas = formulaService.getAll();
        List<FormulaResource> resources = mapper.toResourceList(formulas);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormulaResource> getFormulaById(@PathVariable Long id) {
        Formula formula = formulaService.getById(id);
        FormulaResource resource = mapper.toResource(formula);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FormulaResource> createFormula(@RequestBody CreateFormulaResource resource) {
        Topic topic = topicService.getById(resource.getTopicId());

        Formula formula = new Formula();
        formula.setTopic(topic);
        formula.setTitle(resource.getTitle());
        formula.setExpression(resource.getExpression());
        formula.setImageUrl(resource.getImageUrl());
        Formula savedFormula = formulaService.create(formula);
        return new ResponseEntity<>(mapper.toResource(savedFormula), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormulaResource> updateFormula(@PathVariable Long id, @RequestBody UpdateFormulaResource resource) {
        Topic topic = topicService.getById(resource.getTopicId());

        Formula formula = mapper.toModel(resource);
        formula.setTopic(topic);
        Formula updatedFormula = formulaService.update(id, formula);

        return new ResponseEntity<>(mapper.toResource(updatedFormula), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFormula(@PathVariable Long id) {
        formulaService.delete(id);
        return ResponseEntity.ok().build();
    }
}

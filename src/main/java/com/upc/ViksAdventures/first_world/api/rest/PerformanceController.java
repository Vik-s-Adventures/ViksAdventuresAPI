package com.upc.ViksAdventures.first_world.api.rest;

import com.upc.ViksAdventures.first_world.domain.model.Performance;
import com.upc.ViksAdventures.first_world.domain.model.Competence;
import com.upc.ViksAdventures.first_world.domain.service.PerformanceService;
import com.upc.ViksAdventures.first_world.domain.service.CompetenceService;
import com.upc.ViksAdventures.first_world.mapping.PerformanceMapper;
import com.upc.ViksAdventures.first_world.resource.PerformanceResource;
import com.upc.ViksAdventures.first_world.resource.CreatePerformanceResource;
import com.upc.ViksAdventures.first_world.resource.UpdatePerformanceResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/performances", produces = "application/json")
public class PerformanceController {

    private final PerformanceService performanceService;
    private final CompetenceService competenceService;
    private final PerformanceMapper mapper;

    @Autowired
    public PerformanceController(PerformanceService performanceService, PerformanceMapper mapper, CompetenceService competenceService) {
        this.performanceService = performanceService;
        this.mapper = mapper;
        this.competenceService = competenceService;
    }

    @GetMapping
    public ResponseEntity<List<PerformanceResource>> getAllPerformances() {
        List<Performance> performances = performanceService.getAll();
        List<PerformanceResource> resources = mapper.toResourceList(performances);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerformanceResource> getPerformanceById(@PathVariable Long id) {
        Performance performance = performanceService.getById(id);
        PerformanceResource resource = mapper.toResource(performance);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PerformanceResource> createPerformance(@RequestBody CreatePerformanceResource resource) {
        Competence competence = competenceService.getById(resource.getCompetenceId());

        Performance performance = new Performance();
        performance.setCompetence(competence);
        performance.setDescription(resource.getDescription());
        performance.setImageUrl(resource.getImageUrl());

        Performance savedPerformance = performanceService.create(performance);
        return new ResponseEntity<>(mapper.toResource(savedPerformance), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerformanceResource> updatePerformance(@PathVariable Long id, @RequestBody UpdatePerformanceResource resource) {
        Competence competence = competenceService.getById(resource.getCompetenceId());

        Performance performance = mapper.toModel(resource);
        performance.setCompetence(competence);
        Performance updatedPerformance = performanceService.update(id, performance);

        return new ResponseEntity<>(mapper.toResource(updatedPerformance), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerformance(@PathVariable Long id) {
        // Eliminamos el Performance
        performanceService.delete(id);
        return ResponseEntity.ok().build();
    }
}

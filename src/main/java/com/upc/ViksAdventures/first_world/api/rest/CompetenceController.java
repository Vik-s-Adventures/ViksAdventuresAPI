package com.upc.ViksAdventures.first_world.api.rest;

import com.upc.ViksAdventures.first_world.domain.model.Competence;
import com.upc.ViksAdventures.first_world.domain.service.CompetenceService;
import com.upc.ViksAdventures.first_world.mapping.CompetenceMapper;
import com.upc.ViksAdventures.first_world.resource.CompetenceResource;
import com.upc.ViksAdventures.first_world.resource.CreateCompetenceResource;
import com.upc.ViksAdventures.first_world.resource.UpdateCompetenceResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/competences", produces = "application/json")
public class CompetenceController {

    private final CompetenceService competenceService;
    private final CompetenceMapper mapper;

    @Autowired
    public CompetenceController(CompetenceService competenceService, CompetenceMapper mapper) {
        this.competenceService = competenceService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<CompetenceResource>> getAllCompetences() {
        List<Competence> competences = competenceService.getAll();
        List<CompetenceResource> resources = mapper.toResourceList(competences);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetenceResource> getCompetenceById(@PathVariable Long id) {
        Competence competence = competenceService.getById(id);
        CompetenceResource resource = mapper.toResource(competence);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CompetenceResource> createCompetence(@RequestBody CreateCompetenceResource resource) {
        Competence competence = mapper.toModel(resource);
        Competence savedCompetence = competenceService.create(competence);
        return new ResponseEntity<>(mapper.toResource(savedCompetence), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetenceResource> updateCompetence(@PathVariable Long id, @RequestBody UpdateCompetenceResource resource) {
        Competence competence = mapper.toModel(resource);
        Competence updatedCompetence = competenceService.update(id, competence);
        CompetenceResource competenceResource = mapper.toResource(updatedCompetence);
        return new ResponseEntity<>(competenceResource, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompetence(@PathVariable Long id) {
        competenceService.delete(id);
        return ResponseEntity.ok().build();
    }
}

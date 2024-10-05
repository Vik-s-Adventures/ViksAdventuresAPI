package com.upc.ViksAdventures.first_world.api.rest;

import com.upc.ViksAdventures.first_world.domain.model.Definition;
import com.upc.ViksAdventures.first_world.domain.service.DefinitionService;
import com.upc.ViksAdventures.first_world.mapping.DefinitionMapper;
import com.upc.ViksAdventures.first_world.resource.DefinitionResource;
import com.upc.ViksAdventures.first_world.resource.CreateDefinitionResource;
import com.upc.ViksAdventures.first_world.resource.UpdateDefinitionResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/definitions", produces = "application/json")
public class DefinitionController {

    private final DefinitionService definitionService;
    private final DefinitionMapper mapper;

    @Autowired
    public DefinitionController(DefinitionService definitionService, DefinitionMapper mapper) {
        this.definitionService = definitionService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<DefinitionResource>> getAllDefinitions() {
        List<Definition> definitions = definitionService.getAll();
        List<DefinitionResource> resources = mapper.toResourceList(definitions);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefinitionResource> getDefinitionById(@PathVariable Long id) {
        Definition definition = definitionService.getById(id);
        DefinitionResource resource = mapper.toResource(definition);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DefinitionResource> createDefinition(@RequestBody CreateDefinitionResource resource) {
        Definition definition = mapper.toModel(resource);
        Definition savedDefinition = definitionService.create(definition);
        return new ResponseEntity<>(mapper.toResource(savedDefinition), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DefinitionResource> updateDefinition(@PathVariable Long id, @RequestBody UpdateDefinitionResource resource) {
        Definition definition = mapper.toModel(resource);
        Definition updatedDefinition = definitionService.update(id, definition);
        DefinitionResource definitionResource = mapper.toResource(updatedDefinition);
        return new ResponseEntity<>(definitionResource, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDefinition(@PathVariable Long id) {
        definitionService.delete(id);
        return ResponseEntity.ok().build();
    }
}

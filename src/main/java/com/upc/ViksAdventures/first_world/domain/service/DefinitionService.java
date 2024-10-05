package com.upc.ViksAdventures.first_world.domain.service;

import com.upc.ViksAdventures.first_world.domain.model.Definition;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DefinitionService {
    List<Definition> getAll();
    List<Definition> getByTopicId(Long topicId);
    Definition getById(Long definitionId);
    Definition create(Definition definition);
    Definition update(Long definitionId, Definition definition);
    ResponseEntity<?> delete(Long definitionId);
}

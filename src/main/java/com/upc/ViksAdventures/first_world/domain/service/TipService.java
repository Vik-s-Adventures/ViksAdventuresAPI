package com.upc.ViksAdventures.first_world.domain.service;

import com.upc.ViksAdventures.first_world.domain.model.Tip;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TipService {
    List<Tip> getAll();
    List<Tip> getByTopicId(Long topicId);
    Tip getById(Long tipId);
    Tip create(Tip tip);
    Tip update(Long tipId, Tip tip);
    ResponseEntity<?> delete(Long tipId);
}

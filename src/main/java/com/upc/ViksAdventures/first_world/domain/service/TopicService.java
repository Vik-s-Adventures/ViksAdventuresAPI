package com.upc.ViksAdventures.first_world.domain.service;

import com.upc.ViksAdventures.first_world.domain.model.Topic;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TopicService {
    List<Topic> getAll();
    List<Topic> getByPerformanceId(Long performanceId);
    Topic getById(Long topicId);
    Topic create(Topic topic);
    Topic update(Long topicId, Topic topic);
    ResponseEntity<?> delete(Long topicId);
}

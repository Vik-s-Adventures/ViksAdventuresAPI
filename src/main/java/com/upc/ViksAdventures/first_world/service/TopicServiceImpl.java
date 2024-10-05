package com.upc.ViksAdventures.first_world.service;

import com.upc.ViksAdventures.first_world.domain.model.Topic;
import com.upc.ViksAdventures.first_world.domain.persistence.TopicRepository;
import com.upc.ViksAdventures.first_world.domain.service.TopicService;
import com.upc.ViksAdventures.shared.exception.ResourceNotFoundException;
import com.upc.ViksAdventures.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TopicServiceImpl implements TopicService {

    private static final String ENTITY = "Topic";
    private final TopicRepository topicRepository;
    private final Validator validator;

    public TopicServiceImpl(TopicRepository topicRepository, Validator validator) {
        this.topicRepository = topicRepository;
        this.validator = validator;
    }

    @Override
    public List<Topic> getAll() {
        return topicRepository.findAll();
    }

    @Override
    public List<Topic> getByPerformanceId(Long performanceId) {
        return topicRepository.findTopicsByPerformanceId(performanceId);
    }

    @Override
    public Topic getById(Long topicId) {
        return topicRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, topicId));
    }

    @Override
    public Topic create(Topic topic) {
        Set<ConstraintViolation<Topic>> violations = validator.validate(topic);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }
        return topicRepository.save(topic);
    }

    @Override
    public Topic update(Long topicId, Topic topic) {
        Topic existingTopic = topicRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, topicId));

        existingTopic.setName(topic.getName());
        existingTopic.setPerformance(topic.getPerformance());

        Set<ConstraintViolation<Topic>> violations = validator.validate(existingTopic);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        return topicRepository.save(existingTopic);
    }

    @Override
    public ResponseEntity<?> delete(Long topicId) {
        return topicRepository.findById(topicId).map(topic -> {
            topicRepository.delete(topic);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, topicId));
    }
}

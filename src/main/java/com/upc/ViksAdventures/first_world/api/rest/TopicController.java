package com.upc.ViksAdventures.first_world.api.rest;

import com.upc.ViksAdventures.first_world.domain.model.Performance;
import com.upc.ViksAdventures.first_world.domain.model.Topic;
import com.upc.ViksAdventures.first_world.domain.service.PerformanceService;
import com.upc.ViksAdventures.first_world.domain.service.TopicService;
import com.upc.ViksAdventures.first_world.mapping.TopicMapper;
import com.upc.ViksAdventures.first_world.resource.TopicResource;
import com.upc.ViksAdventures.first_world.resource.CreateTopicResource;
import com.upc.ViksAdventures.first_world.resource.UpdateTopicResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/topics", produces = "application/json")
public class TopicController {
    private final PerformanceService performanceService;
    private final TopicService topicService;
    private final TopicMapper mapper;

    @Autowired
    public TopicController(PerformanceService performanceService, TopicService topicService, TopicMapper mapper) {
        this.performanceService = performanceService;
        this.topicService = topicService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<TopicResource>> getAllTopics() {
        List<Topic> topics = topicService.getAll();
        List<TopicResource> resources = mapper.toResourceList(topics);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResource> getTopicById(@PathVariable Long id) {
        Topic topic = topicService.getById(id);
        TopicResource resource = mapper.toResource(topic);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TopicResource> createTopic(@RequestBody CreateTopicResource resource) {
        Performance performance = performanceService.getById(resource.getPerformanceId());

        Topic topic = new Topic();
        topic.setPerformance(performance);
        topic.setName(resource.getName());

        Topic savedTopic = topicService.create(topic);
        return new ResponseEntity<>(mapper.toResource(savedTopic), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicResource> updateTopic(@PathVariable Long id, @RequestBody UpdateTopicResource resource) {
        Performance performance = performanceService.getById(resource.getPerformanceId());

        Topic topic = mapper.toModel(resource);
        topic.setPerformance(performance);
        Topic updatedTopic = topicService.update(id, topic);

        return new ResponseEntity<>(mapper.toResource(updatedTopic), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable Long id) {
        topicService.delete(id);
        return ResponseEntity.ok().build();
    }
}

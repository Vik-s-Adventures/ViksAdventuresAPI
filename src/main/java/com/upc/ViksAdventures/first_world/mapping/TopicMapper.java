package com.upc.ViksAdventures.first_world.mapping;

import com.upc.ViksAdventures.first_world.domain.model.Topic;
import com.upc.ViksAdventures.first_world.resource.CreateTopicResource;
import com.upc.ViksAdventures.first_world.resource.TopicResource;
import com.upc.ViksAdventures.first_world.resource.UpdateTopicResource;
import com.upc.ViksAdventures.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class TopicMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    public TopicResource toResource(Topic model) {
        return mapper.map(model, TopicResource.class);
    }

    public Topic toModel(CreateTopicResource resource) {
        return mapper.map(resource, Topic.class);
    }

    public Topic toModel(UpdateTopicResource resource) {
        return mapper.map(resource, Topic.class);
    }

    public List<TopicResource> toResourceList(List<Topic> modelList) {
        return modelList.stream()
                .map(this::toResource)
                .collect(Collectors.toList());
    }

    public Page<TopicResource> modelListPage(List<Topic> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, TopicResource.class), pageable, modelList.size());
    }
}

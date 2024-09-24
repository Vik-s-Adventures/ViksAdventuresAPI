package com.upc.ViksAdventures.quiz.mapping;

import com.upc.ViksAdventures.quiz.domain.model.Option;
import com.upc.ViksAdventures.quiz.resource.OptionResource;
import com.upc.ViksAdventures.quiz.resource.CreateOptionResource;
import com.upc.ViksAdventures.quiz.resource.UpdateOptionResource;
import com.upc.ViksAdventures.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class OptionMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    public OptionResource toResource(Option model) {
        return mapper.map(model, OptionResource.class);
    }

    public Option toModel(CreateOptionResource resource) {
        return mapper.map(resource, Option.class);
    }

    public Option toModel(UpdateOptionResource resource) {
        return mapper.map(resource, Option.class);
    }

    public List<OptionResource> toResourceList(List<Option> modelList) {
        return modelList.stream()
                .map(this::toResource)
                .collect(Collectors.toList());
    }

    public Page<OptionResource> modelListPage(List<Option> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, OptionResource.class), pageable, modelList.size());
    }
}
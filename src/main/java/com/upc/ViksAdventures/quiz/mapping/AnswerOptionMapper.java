package com.upc.ViksAdventures.quiz.mapping;

import com.upc.ViksAdventures.quiz.domain.model.AnswerOption;
import com.upc.ViksAdventures.quiz.domain.model.Student;
import com.upc.ViksAdventures.quiz.resource.*;
import com.upc.ViksAdventures.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class AnswerOptionMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    public AnswerOptionResource toResource(AnswerOption model) {
        return mapper.map(model, AnswerOptionResource.class);
    }

    public AnswerOption toModel(CreateAnswerOptionResource resource) {
        return mapper.map(resource, AnswerOption.class);
    }

    public AnswerOption toModel(UpdateAnswerOptionResource resource) {
        return mapper.map(resource, AnswerOption.class);
    }

    public List<AnswerOptionResource> toResourceList(List<AnswerOption> modelList) {
        return modelList.stream()
                .map(this::toResource)
                .collect(Collectors.toList());
    }

    public Page<AnswerOptionResource> modelListPage(List<AnswerOption> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, AnswerOptionResource.class), pageable, modelList.size());
    }
}
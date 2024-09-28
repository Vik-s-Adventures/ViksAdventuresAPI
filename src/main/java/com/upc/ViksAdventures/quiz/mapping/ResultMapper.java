package com.upc.ViksAdventures.quiz.mapping;

import com.upc.ViksAdventures.quiz.domain.model.Result;
import com.upc.ViksAdventures.quiz.resource.CreateResultResource;
import com.upc.ViksAdventures.quiz.resource.ResultResource;
import com.upc.ViksAdventures.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class ResultMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public ResultResource toResource(Result model) {
        return mapper.map(model, ResultResource.class);
    }

    public Result toModel(CreateResultResource resource) {
        return mapper.map(resource, Result.class);
    }

    public List<ResultResource> toResourceList(List<Result> modelList) {
        return modelList.stream()
                .map(this::toResource)
                .collect(Collectors.toList());
    }

    public Page<ResultResource> modelListPage(List<Result> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, ResultResource.class), pageable, modelList.size());
    }
}

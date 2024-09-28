package com.upc.ViksAdventures.quiz.mapping;

import com.upc.ViksAdventures.quiz.domain.model.Response;
import com.upc.ViksAdventures.quiz.resource.CreateResponseResource;
import com.upc.ViksAdventures.quiz.resource.ResponseResource;
import com.upc.ViksAdventures.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class ResponseMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public ResponseResource toResource(Response model) {
        return mapper.map(model, ResponseResource.class);
    }

    public Response toModel(CreateResponseResource resource) {
        return mapper.map(resource, Response.class);
    }

    public List<ResponseResource> toResourceList(List<Response> modelList) {
        return modelList.stream()
                .map(this::toResource)
                .collect(Collectors.toList());
    }

    public Page<ResponseResource> modelListPage(List<Response> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, ResponseResource.class), pageable, modelList.size());
    }
}

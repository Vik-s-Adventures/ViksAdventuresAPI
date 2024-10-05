package com.upc.ViksAdventures.first_world.mapping;

import com.upc.ViksAdventures.first_world.domain.model.Performance;
import com.upc.ViksAdventures.first_world.resource.CreatePerformanceResource;
import com.upc.ViksAdventures.first_world.resource.PerformanceResource;
import com.upc.ViksAdventures.first_world.resource.UpdatePerformanceResource;
import com.upc.ViksAdventures.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class PerformanceMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    public PerformanceResource toResource(Performance model) {
        return mapper.map(model, PerformanceResource.class);
    }

    public Performance toModel(CreatePerformanceResource resource) {
        return mapper.map(resource, Performance.class);
    }

    public Performance toModel(UpdatePerformanceResource resource) {
        return mapper.map(resource, Performance.class);
    }

    public List<PerformanceResource> toResourceList(List<Performance> modelList) {
        return modelList.stream()
                .map(this::toResource)
                .collect(Collectors.toList());
    }

    public Page<PerformanceResource> modelListPage(List<Performance> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, PerformanceResource.class), pageable, modelList.size());
    }
}

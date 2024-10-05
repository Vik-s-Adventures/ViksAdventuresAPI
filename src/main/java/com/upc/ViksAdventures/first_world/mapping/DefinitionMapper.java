package com.upc.ViksAdventures.first_world.mapping;

import com.upc.ViksAdventures.first_world.domain.model.Definition;
import com.upc.ViksAdventures.first_world.resource.CreateDefinitionResource;
import com.upc.ViksAdventures.first_world.resource.DefinitionResource;
import com.upc.ViksAdventures.first_world.resource.UpdateDefinitionResource;
import com.upc.ViksAdventures.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class DefinitionMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    public DefinitionResource toResource(Definition model) {
        return mapper.map(model, DefinitionResource.class);
    }

    public Definition toModel(CreateDefinitionResource resource) {
        return mapper.map(resource, Definition.class);
    }

    public Definition toModel(UpdateDefinitionResource resource) {
        return mapper.map(resource, Definition.class);
    }

    public List<DefinitionResource> toResourceList(List<Definition> modelList) {
        return modelList.stream()
                .map(this::toResource)
                .collect(Collectors.toList());
    }

    public Page<DefinitionResource> modelListPage(List<Definition> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, DefinitionResource.class), pageable, modelList.size());
    }
}

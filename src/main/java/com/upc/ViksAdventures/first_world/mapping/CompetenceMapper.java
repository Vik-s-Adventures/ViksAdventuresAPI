package com.upc.ViksAdventures.first_world.mapping;

import com.upc.ViksAdventures.first_world.domain.model.Competence;
import com.upc.ViksAdventures.first_world.resource.CompetenceResource;
import com.upc.ViksAdventures.first_world.resource.CreateCompetenceResource;
import com.upc.ViksAdventures.first_world.resource.UpdateCompetenceResource;
import com.upc.ViksAdventures.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class CompetenceMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    public CompetenceResource toResource(Competence model) {
        return mapper.map(model, CompetenceResource.class);
    }

    public Competence toModel(CreateCompetenceResource resource) {
        return mapper.map(resource, Competence.class);
    }

    public Competence toModel(UpdateCompetenceResource resource) {
        return mapper.map(resource, Competence.class);
    }

    public List<CompetenceResource> toResourceList(List<Competence> modelList) {
        return modelList.stream()
                .map(this::toResource)
                .collect(Collectors.toList());
    }

    public Page<CompetenceResource> modelListPage(List<Competence> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, CompetenceResource.class), pageable, modelList.size());
    }
}

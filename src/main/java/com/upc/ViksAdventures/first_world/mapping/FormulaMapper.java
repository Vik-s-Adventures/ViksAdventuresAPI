package com.upc.ViksAdventures.first_world.mapping;

import com.upc.ViksAdventures.first_world.domain.model.Formula;
import com.upc.ViksAdventures.first_world.resource.CreateFormulaResource;
import com.upc.ViksAdventures.first_world.resource.FormulaResource;
import com.upc.ViksAdventures.first_world.resource.UpdateFormulaResource;
import com.upc.ViksAdventures.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class FormulaMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    public FormulaResource toResource(Formula model) {
        return mapper.map(model, FormulaResource.class);
    }

    public Formula toModel(CreateFormulaResource resource) {
        return mapper.map(resource, Formula.class);
    }

    public Formula toModel(UpdateFormulaResource resource) {
        return mapper.map(resource, Formula.class);
    }

    public List<FormulaResource> toResourceList(List<Formula> modelList) {
        return modelList.stream()
                .map(this::toResource)
                .collect(Collectors.toList());
    }

    public Page<FormulaResource> modelListPage(List<Formula> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, FormulaResource.class), pageable, modelList.size());
    }
}

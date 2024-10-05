package com.upc.ViksAdventures.first_world.mapping;

import com.upc.ViksAdventures.first_world.domain.model.Tip;
import com.upc.ViksAdventures.first_world.resource.CreateTipResource;
import com.upc.ViksAdventures.first_world.resource.TipResource;
import com.upc.ViksAdventures.first_world.resource.UpdateTipResource;
import com.upc.ViksAdventures.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class TipMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    public TipResource toResource(Tip model) {
        return mapper.map(model, TipResource.class);
    }

    public Tip toModel(CreateTipResource resource) {
        return mapper.map(resource, Tip.class);
    }

    public Tip toModel(UpdateTipResource resource) {
        return mapper.map(resource, Tip.class);
    }

    public List<TipResource> toResourceList(List<Tip> modelList) {
        return modelList.stream()
                .map(this::toResource)
                .collect(Collectors.toList());
    }

    public Page<TipResource> modelListPage(List<Tip> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, TipResource.class), pageable, modelList.size());
    }
}

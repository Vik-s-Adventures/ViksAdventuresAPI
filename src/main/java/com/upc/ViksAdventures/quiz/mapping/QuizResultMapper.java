package com.upc.ViksAdventures.quiz.mapping;

import com.upc.ViksAdventures.quiz.domain.model.QuizResult;
import com.upc.ViksAdventures.quiz.resource.CreateQuizResultResource;
import com.upc.ViksAdventures.quiz.resource.QuizResultResource;
import com.upc.ViksAdventures.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class QuizResultMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public QuizResultResource toResource(QuizResult model) {
        return mapper.map(model, QuizResultResource.class);
    }

    public QuizResult toModel(CreateQuizResultResource resource) {
        return mapper.map(resource, QuizResult.class);
    }

    public Page<QuizResultResource> modelListPage(List<QuizResult> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, QuizResultResource.class), pageable, modelList.size());
    }
}

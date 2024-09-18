package com.upc.ViksAdventures.quiz.mapping;

import com.upc.ViksAdventures.quiz.domain.model.Quiz;
import com.upc.ViksAdventures.quiz.resource.CreateQuizResource;
import com.upc.ViksAdventures.quiz.resource.QuizResource;
import com.upc.ViksAdventures.quiz.resource.UpdateQuizResource;
import com.upc.ViksAdventures.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class QuizMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public QuizResource toResource(Quiz model) {
        return mapper.map(model, QuizResource.class);
    }

    public Quiz toModel(CreateQuizResource resource) {
        return mapper.map(resource, Quiz.class);
    }

    public Quiz toModel(UpdateQuizResource resource) {
        return mapper.map(resource, Quiz.class);
    }

    public Page<QuizResource> modelListPage(List<Quiz> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, QuizResource.class), pageable, modelList.size());
    }
}

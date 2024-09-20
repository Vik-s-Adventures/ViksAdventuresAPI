package com.upc.ViksAdventures.quiz.mapping;

import com.upc.ViksAdventures.quiz.domain.model.AnswerOption;
import com.upc.ViksAdventures.quiz.domain.model.Question;
import com.upc.ViksAdventures.quiz.resource.AnswerOptionResource;
import com.upc.ViksAdventures.quiz.resource.QuestionResource;
import com.upc.ViksAdventures.quiz.resource.CreateQuestionResource;
import com.upc.ViksAdventures.quiz.resource.UpdateQuestionResource;
import com.upc.ViksAdventures.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionMapper implements Serializable {
    
    @Autowired
    private EnhancedModelMapper mapper;

    public QuestionResource toResource(Question model) {
        return mapper.map(model, QuestionResource.class);
    }

    public Question toModel(CreateQuestionResource resource) {
        return mapper.map(resource, Question.class);
    }

    public Question toModel(UpdateQuestionResource resource) {
        return mapper.map(resource, Question.class);
    }

    public List<QuestionResource> toResourceList(List<Question> modelList) {
        return modelList.stream()
                .map(this::toResource)
                .collect(Collectors.toList());
    }

    public Page<QuestionResource> modelListPage(List<Question> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, QuestionResource.class), pageable, modelList.size());
    }
}

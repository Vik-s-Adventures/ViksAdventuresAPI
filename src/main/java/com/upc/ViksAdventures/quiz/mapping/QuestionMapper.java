package com.upc.ViksAdventures.quiz.mapping;

import com.upc.ViksAdventures.quiz.domain.model.Performance;
import com.upc.ViksAdventures.quiz.domain.model.Question;
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
        QuestionResource resource = mapper.map(model, QuestionResource.class);
        resource.setPerformance(model.getPerformance().toString());
        return resource;
    }


    public Question toModel(CreateQuestionResource resource) {
        Question question = mapper.map(resource, Question.class);
        question.setPerformance(Performance.values()[resource.getPerformance() - 1]);
        return question;
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


package com.upc.ViksAdventures.quiz.mapping;

import com.upc.ViksAdventures.quiz.domain.model.AnswerOption;
import com.upc.ViksAdventures.quiz.domain.model.Question;
import com.upc.ViksAdventures.quiz.domain.model.Skill;
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

    // Convertir Question a QuestionResource, mapeando el Skill como String
    public QuestionResource toResource(Question model) {
        QuestionResource resource = mapper.map(model, QuestionResource.class);
        // Aquí conviertes el Skill de enum a String
        resource.setSkill(model.getSkill().name());
        return resource;
    }

    // Convertir CreateQuestionResource a Question, mapeando el Skill como int a enum
    public Question toModel(CreateQuestionResource resource) {
        Question question = mapper.map(resource, Question.class);
        // Aquí conviertes el valor numérico del Skill al enum correspondiente
        question.setSkill(Skill.values()[resource.getSkill()]);
        return question;
    }

    // Convertir UpdateQuestionResource a Question
    public Question toModel(UpdateQuestionResource resource) {
        Question question = mapper.map(resource, Question.class);
        // Aquí también puedes hacer lo mismo si usas Skill en el UpdateResource
        question.setSkill(Skill.values()[resource.getSkill()]);
        return question;
    }

    // Mapear listas
    public List<QuestionResource> toResourceList(List<Question> modelList) {
        return modelList.stream()
                .map(this::toResource)
                .collect(Collectors.toList());
    }

    public Page<QuestionResource> modelListPage(List<Question> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, QuestionResource.class), pageable, modelList.size());
    }
}


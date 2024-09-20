package com.upc.ViksAdventures.quiz.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionResource {
    private Long id;
    private String questionText;
    private String skill;
    private Long quizId;
    private List<AnswerOptionResource> answerOptions;
}

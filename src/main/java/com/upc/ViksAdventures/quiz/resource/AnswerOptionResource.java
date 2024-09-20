package com.upc.ViksAdventures.quiz.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerOptionResource {
    private Long id;
    private String answerText;
    private boolean isCorrect;
    private Long questionId;
}

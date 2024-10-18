package com.upc.ViksAdventures.quiz.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionResource {
    private Long id;
    private String questionText;
    private String imageUrl;
    private String performance;
    private Long quizId;
    private List<OptionResource> options;
}

package com.upc.ViksAdventures.quiz.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuizResource {
    private Long id;
    private String title;
    private String description;
    private List<QuestionResource> questions;
    private List<ResultResource> results;
}

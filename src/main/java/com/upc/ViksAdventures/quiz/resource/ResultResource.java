package com.upc.ViksAdventures.quiz.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultResource {
    private Long id;
    private int score;
    private Long quizId;
    private Long studentId;
}


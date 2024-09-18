package com.upc.ViksAdventures.quiz.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuizResultResource {
    private Long id;
    private int score;
    private Long studentId;
    private Long quizId;
    private List<StudentResponseResource> studentResponses;
}


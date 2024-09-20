package com.upc.ViksAdventures.quiz.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponseResource {
    private Long id;
    private Long quizResultId;
    private Long answerOptionId;
}

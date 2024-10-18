package com.upc.ViksAdventures.quiz.resource;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateResultResource {
    @NotNull
    private int score;
    @NotNull
    private Long quizId;
    @NotNull
    private Long studentId;
}

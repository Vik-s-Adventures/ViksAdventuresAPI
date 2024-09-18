package com.upc.ViksAdventures.quiz.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuizResultResource {
    @NotBlank
    @NotNull
    private int score;

    @NotBlank
    @NotNull
    private Long studentId;

    @NotBlank
    @NotNull
    private Long quizId;
}

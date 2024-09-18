package com.upc.ViksAdventures.quiz.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStudentResponseResource {
    @NotNull
    @NotBlank
    private int timeTaken;

    @NotNull
    @NotBlank
    private Long quizResultId;

    @NotNull
    @NotBlank
    private Long answerOptionId;
}

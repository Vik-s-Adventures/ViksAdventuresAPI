package com.upc.ViksAdventures.quiz.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAnswerOptionResource {
    @NotNull
    @NotBlank
    @Size(max = 60)
    private String answerText;

    @NotNull
    @NotBlank
    private boolean isCorrect;

    @NotNull
    @NotBlank
    private Long questionId;
}

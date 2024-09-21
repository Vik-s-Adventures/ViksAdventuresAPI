package com.upc.ViksAdventures.quiz.resource;

import com.upc.ViksAdventures.quiz.domain.model.Performance;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateQuestionResource {
    @NotNull
    private Performance performance;

    @NotNull
    @NotBlank
    @Size(max = 60)
    private String questionText;

    @NotNull
    private Long quizId;
}

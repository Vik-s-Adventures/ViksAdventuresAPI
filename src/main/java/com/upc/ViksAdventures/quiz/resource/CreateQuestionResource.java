package com.upc.ViksAdventures.quiz.resource;

import com.upc.ViksAdventures.quiz.domain.model.Performance;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuestionResource {
    @NotNull
    private Performance performance;

    @NotNull
    private String questionText;

    private String imageUrl;

    @NotNull
    private Long quizId;
}

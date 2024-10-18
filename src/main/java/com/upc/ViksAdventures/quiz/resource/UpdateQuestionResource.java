package com.upc.ViksAdventures.quiz.resource;

import com.upc.ViksAdventures.quiz.domain.model.Performance;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateQuestionResource {
    @NotNull
    private Performance performance;

    @NotNull
    private String questionText;

    private String imageUrl;

    @NotNull
    private Long quizId;
}

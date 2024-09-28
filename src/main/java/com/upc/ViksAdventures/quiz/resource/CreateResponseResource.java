package com.upc.ViksAdventures.quiz.resource;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateResponseResource {
    @NotNull
    private Long studentId;

    @NotNull
    private Long optionId;
}

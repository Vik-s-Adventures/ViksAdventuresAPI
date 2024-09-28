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
public class UpdateOptionResource {
    @NotNull
    @NotBlank
    @Size(max = 60)
    private String text;

    @NotNull
    private boolean correct;

    @NotNull
    private Long questionId;
}

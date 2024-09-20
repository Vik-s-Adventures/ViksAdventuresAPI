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
public class UpdateQuizResource {
    @NotNull
    @NotBlank
    @Size(max = 60)
    private String title;

    @NotNull
    @NotBlank
    @Size(max = 80)
    private String description;
}

package com.upc.ViksAdventures.quiz.resource;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateQuizResource {
    @NotNull
    @Size(max = 60)
    private String title;

    @NotNull
    @Size(max = 80)
    private String description;
}

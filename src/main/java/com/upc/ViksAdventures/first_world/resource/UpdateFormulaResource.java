package com.upc.ViksAdventures.first_world.resource;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFormulaResource {
    private String title;

    @NotNull
    @Size(max = 255)
    private String expression;

    private String imageUrl;

    @NotNull
    private Long topicId;
}

package com.upc.ViksAdventures.first_world.resource;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateTipResource {
    @NotNull
    @Size(max = 255)
    private String tipText;

    @NotNull
    private Long topicId;
}

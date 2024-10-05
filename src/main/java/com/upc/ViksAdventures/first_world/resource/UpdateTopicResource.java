package com.upc.ViksAdventures.first_world.resource;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTopicResource {
    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    private Long performanceId;
}

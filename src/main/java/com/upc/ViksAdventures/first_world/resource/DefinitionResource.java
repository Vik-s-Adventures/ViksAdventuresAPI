package com.upc.ViksAdventures.first_world.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class DefinitionResource {
    private Long id;
    private String description;
    private Long topicId;
}

package com.upc.ViksAdventures.first_world.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class DefinitionResource {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private Long topicId;
}

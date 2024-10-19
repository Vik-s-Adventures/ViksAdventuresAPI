package com.upc.ViksAdventures.first_world.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class TipResource {
    private Long id;
    private String tipText;
    private String imageUrl;
    private Long topicId;
}

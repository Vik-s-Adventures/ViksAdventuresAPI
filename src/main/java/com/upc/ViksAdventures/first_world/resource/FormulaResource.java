package com.upc.ViksAdventures.first_world.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class FormulaResource {
    private Long id;
    private String expression;
    private String imageUrl;
    private Long topicId;
}

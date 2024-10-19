package com.upc.ViksAdventures.first_world.resource;

import lombok.*;

import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceResource {
    private Long id;
    private String description;
    private String imageUrl;
    private Long competenceId;
    private List<TopicResource> topics;
}

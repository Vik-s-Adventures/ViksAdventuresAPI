package com.upc.ViksAdventures.first_world.resource;

import lombok.*;

import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CompetenceResource {
    private Long id;
    private String title;
    private String description;
    private List<PerformanceResource> performances;
}

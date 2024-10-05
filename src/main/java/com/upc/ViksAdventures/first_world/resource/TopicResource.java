package com.upc.ViksAdventures.first_world.resource;

import lombok.*;

import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class TopicResource {
    private Long id;
    private String name;
    private Long performanceId;
    private List<DefinitionResource> definitions;
    private List<FormulaResource> formulas;
    private List<TipResource> tips;
}

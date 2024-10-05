package com.upc.ViksAdventures.first_world.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("VikAdventureFirstWorldMappingConfiguration")
public class FirstWorldMappingConfiguration {

    @Bean
    public CompetenceMapper competenceMapper() {
        return new CompetenceMapper();
    }

    @Bean
    public PerformanceMapper performanceMapper() {
        return new PerformanceMapper();
    }

    @Bean
    public TopicMapper topicMapper() {
        return new TopicMapper();
    }

    @Bean
    public FormulaMapper formulaMapper() {
        return new FormulaMapper();
    }

    @Bean
    public TipMapper tipMapper() {
        return new TipMapper();
    }

    @Bean
    public DefinitionMapper definitionMapper() {
        return new DefinitionMapper();
    }
}

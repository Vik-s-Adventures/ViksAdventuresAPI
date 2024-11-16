package com.upc.ViksAdventures.profile.mapping;


import com.upc.ViksAdventures.quiz.mapping.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("VikAdventureProfileMappingConfiguration")
public class ProfileMappingConfiguration {
    @Bean
    public StudentMapper studentMapper() {
        return new StudentMapper();
    }
}

package com.upc.ViksAdventures.quiz.mapping;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("VikAdventureQuizMappingConfiguration")
public class QuizMappingConfiguration {
    @Bean
    public OptionMapper optionMapper() {
        return new OptionMapper();
    }

    @Bean
    public QuestionMapper questionMapper() {
        return new QuestionMapper();
    }

    @Bean
    public QuizMapper quizMapper() {
        return new QuizMapper();
    }

    @Bean
    public ResultMapper resultMapper() {
        return new ResultMapper();
    }

    @Bean
    public ResponseMapper responseMapper() {
        return new ResponseMapper();
    }
}

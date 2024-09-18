package com.upc.ViksAdventures.quiz.mapping;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("VikAdventureMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public AnswerOptionMapper answerOptionMapper() {
        return new AnswerOptionMapper();
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
    public QuizResultMapper quizResultMapper() {
        return new QuizResultMapper();
    }

    @Bean
    public StudentMapper studentMapper() {
        return new StudentMapper();
    }

    @Bean
    public StudentResponseMapper studentResponseMapper() {
        return new StudentResponseMapper();
    }
}

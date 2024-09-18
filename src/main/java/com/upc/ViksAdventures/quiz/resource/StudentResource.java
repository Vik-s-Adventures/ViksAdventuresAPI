package com.upc.ViksAdventures.quiz.resource;

import com.upc.ViksAdventures.quiz.domain.model.QuizResult;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentResource {
    private Long id;
    private String firstName;
    private String lastName;
    private int gradeLevel;
    private String birthDate;
    private List<QuizResult> quizResults;
}

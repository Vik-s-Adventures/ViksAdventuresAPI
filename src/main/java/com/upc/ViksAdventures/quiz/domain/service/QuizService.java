package com.upc.ViksAdventures.quiz.domain.service;

import com.upc.ViksAdventures.quiz.domain.model.Quiz;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizService {
    List<Quiz> getAll();
    Quiz getBydId(Long quizId);
    Quiz create(Quiz quiz);
    Quiz update(Long id, Quiz quiz);
    ResponseEntity<?> delete(Long quizId);
}

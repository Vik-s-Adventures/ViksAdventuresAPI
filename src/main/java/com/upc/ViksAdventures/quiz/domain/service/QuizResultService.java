package com.upc.ViksAdventures.quiz.domain.service;

import com.upc.ViksAdventures.quiz.domain.model.QuizResult;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizResultService {
    List<QuizResult> getAll();
    QuizResult getBydId(Long quizResultId);
    QuizResult create(QuizResult quizResult);
    ResponseEntity<?> delete(Long quizResultId);
}

package com.upc.ViksAdventures.quiz.domain.service;

import com.upc.ViksAdventures.quiz.domain.model.Quiz;
import com.upc.ViksAdventures.quiz.domain.model.Result;
import com.upc.ViksAdventures.quiz.domain.model.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResultService {
    List<Result> getAll();
    Result getBydId(Long quizResultId);
    void updateOrCreateResult(Student student, Quiz quiz);
    ResponseEntity<?> delete(Long quizResultId);
}

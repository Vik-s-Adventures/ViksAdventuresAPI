package com.upc.ViksAdventures.quiz.domain.service;

import com.upc.ViksAdventures.quiz.domain.model.Question;
import com.upc.ViksAdventures.quiz.domain.model.Skill;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    List<Question> getAll();
    List<Question> getQuestionsByQuizId(Long quizId);
    List<Question> getQuestionsByQuizIdAndSkill(Long quizId, Skill skill);
    Optional<Question> getBydId(Long questionId);
    Question create(Question question);
    Question update(Long id, Question question);
    ResponseEntity<?> delete(Long questionId);
}

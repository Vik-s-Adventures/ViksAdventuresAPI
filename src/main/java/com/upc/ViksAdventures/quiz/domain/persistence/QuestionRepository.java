package com.upc.ViksAdventures.quiz.domain.persistence;

import com.upc.ViksAdventures.quiz.domain.model.Question;

import java.util.List;

public interface QuestionRepository {
    List<Question> findQuestionsByQuizId(Long quizId);
    List<Question> findQuestionsByQuizIdAndPerformance(Long quizId, String performance);
}

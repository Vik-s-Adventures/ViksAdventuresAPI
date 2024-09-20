package com.upc.ViksAdventures.quiz.domain.persistence;

import com.upc.ViksAdventures.quiz.domain.model.Question;
import com.upc.ViksAdventures.quiz.domain.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findQuestionsByQuizId(Long quizId);
    List<Question> findQuestionByQuizIdAndSkill(Long quizId, Skill skill);
}
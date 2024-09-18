package com.upc.ViksAdventures.quiz.domain.persistence;

import com.upc.ViksAdventures.quiz.domain.model.AnswerOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerOptionRepository extends JpaRepository<AnswerOption, Long> {
    List<AnswerOption> findByQuestionId(Long questionId);
    List<AnswerOption> findAnswerOptionByCorrect(Boolean isCorrect);
}

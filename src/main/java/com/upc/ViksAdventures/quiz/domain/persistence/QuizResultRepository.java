package com.upc.ViksAdventures.quiz.domain.persistence;

import com.upc.ViksAdventures.quiz.domain.model.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {
    List<QuizResult> findByQuizId(Long quizId);
    List<QuizResult> findByStudentId(Long studentId);
    List<QuizResult> findByQuizIdAndStudentId(Long quizId, Long studentId);
}

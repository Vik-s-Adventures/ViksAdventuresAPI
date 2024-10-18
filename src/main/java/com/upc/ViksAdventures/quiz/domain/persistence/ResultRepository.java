package com.upc.ViksAdventures.quiz.domain.persistence;

import com.upc.ViksAdventures.quiz.domain.model.Quiz;
import com.upc.ViksAdventures.quiz.domain.model.Result;
import com.upc.ViksAdventures.quiz.domain.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    @Query("SELECT r FROM Result r WHERE r.student = :student AND EXISTS " +
            "(SELECT 1 FROM Response res WHERE res.student = :student " +
            "AND res.option.question.quiz = :quiz)")
    Result findByStudentAndQuiz(Student student, Quiz quiz);
}

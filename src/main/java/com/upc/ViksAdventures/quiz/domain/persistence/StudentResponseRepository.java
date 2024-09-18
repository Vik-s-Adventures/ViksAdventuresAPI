package com.upc.ViksAdventures.quiz.domain.persistence;

import com.upc.ViksAdventures.quiz.domain.model.StudentResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentResponseRepository extends JpaRepository<StudentResponse, Long> {
    List<StudentResponse> findByQuizResultId(Long quizResultId);
}

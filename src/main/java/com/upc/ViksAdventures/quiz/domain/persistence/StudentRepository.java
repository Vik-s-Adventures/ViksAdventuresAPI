package com.upc.ViksAdventures.quiz.domain.persistence;

import com.upc.ViksAdventures.quiz.domain.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentById(Long studentId);
}

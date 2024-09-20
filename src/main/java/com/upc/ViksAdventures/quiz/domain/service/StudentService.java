package com.upc.ViksAdventures.quiz.domain.service;

import com.upc.ViksAdventures.quiz.domain.model.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {
    List<Student> getAll();
    Student getBydId(Long studentId);
    Student create(Student student);
    Student update(Long id, Student student);
    ResponseEntity<?> delete(Long studentId);
}

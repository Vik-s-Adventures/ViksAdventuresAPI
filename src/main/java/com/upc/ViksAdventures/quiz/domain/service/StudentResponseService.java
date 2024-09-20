package com.upc.ViksAdventures.quiz.domain.service;

import com.upc.ViksAdventures.quiz.domain.model.StudentResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentResponseService {
    List<StudentResponse> getAll();
    StudentResponse getBydId(Long studentResponseId);
    StudentResponse create(StudentResponse studentResponse);
    ResponseEntity<?> delete(Long studentResponseId);
}

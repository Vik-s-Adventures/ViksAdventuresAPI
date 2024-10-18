package com.upc.ViksAdventures.quiz.domain.service;

import com.upc.ViksAdventures.quiz.domain.model.Quiz;
import com.upc.ViksAdventures.quiz.domain.model.Response;
import com.upc.ViksAdventures.quiz.domain.model.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResponseService {
    List<Response> getAll();
    Response getBydId(Long studentResponseId);
    List<Response> getResponsesByStudentAndQuiz(Student student, Quiz quiz);
    Response create(Response response);
    ResponseEntity<?> delete(Long studentResponseId);
}

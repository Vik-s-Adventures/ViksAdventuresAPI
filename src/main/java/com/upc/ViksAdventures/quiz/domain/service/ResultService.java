package com.upc.ViksAdventures.quiz.domain.service;

import com.upc.ViksAdventures.quiz.domain.model.Result;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResultService {
    List<Result> getAll();
    Result getBydId(Long quizResultId);
    Result create(Result result);
    ResponseEntity<?> delete(Long quizResultId);
}

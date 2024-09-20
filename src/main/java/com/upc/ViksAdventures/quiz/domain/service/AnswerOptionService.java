package com.upc.ViksAdventures.quiz.domain.service;

import com.upc.ViksAdventures.quiz.domain.model.AnswerOption;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AnswerOptionService {
    List<AnswerOption> getAll();
    AnswerOption getBydId(Long answerOptionId);
    AnswerOption create(AnswerOption answerOption);
    AnswerOption update(Long id, AnswerOption answerOption);
    ResponseEntity<?> delete(Long answerOptionId);
}

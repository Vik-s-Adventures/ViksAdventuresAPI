package com.upc.ViksAdventures.quiz.domain.service;

import com.upc.ViksAdventures.quiz.domain.model.Option;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OptionService {
    List<Option> getAll();
    Option getBydId(Long answerOptionId);
    Option create(Option option);
    Option update(Long id, Option option);
    ResponseEntity<?> delete(Long answerOptionId);
}

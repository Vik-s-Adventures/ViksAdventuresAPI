package com.upc.ViksAdventures.quiz.domain.service;

import com.upc.ViksAdventures.quiz.domain.model.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResponseService {
    List<Response> getAll();
    Response getBydId(Long studentResponseId);
    Response create(Response response);
    ResponseEntity<?> delete(Long studentResponseId);
}

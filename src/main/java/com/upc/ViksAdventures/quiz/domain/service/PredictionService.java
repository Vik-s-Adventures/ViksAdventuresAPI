package com.upc.ViksAdventures.quiz.domain.service;

import java.util.Map;

public interface PredictionService {
    Map<String, Object> enviarDatosYRecibirPrediccion(Map<String, Object> studentData);
}

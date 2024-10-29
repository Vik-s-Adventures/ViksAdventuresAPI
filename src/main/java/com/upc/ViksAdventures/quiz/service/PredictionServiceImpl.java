package com.upc.ViksAdventures.quiz.service;

import com.upc.ViksAdventures.quiz.domain.service.PredictionService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class PredictionServiceImpl implements PredictionService {

    private final String MODEL_URL = "http://127.0.0.1:5000/predict"; // URL de la API Flask

    @Override
    public Map<String, Object> enviarDatosYRecibirPrediccion(Map<String, Object> studentData) {
        RestTemplate restTemplate = new RestTemplate();

        // Configurar los encabezados de la solicitud
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Crear la solicitud con los datos del estudiante
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(studentData, headers);

        // Realizar la solicitud POST
        ResponseEntity<Map> response = restTemplate.exchange(
                MODEL_URL, HttpMethod.POST, requestEntity, Map.class);

        // Devolver el cuerpo de la respuesta como un Map
        return response.getBody();
    }
}

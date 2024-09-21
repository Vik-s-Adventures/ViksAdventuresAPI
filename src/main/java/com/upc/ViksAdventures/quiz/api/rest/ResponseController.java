package com.upc.ViksAdventures.quiz.api.rest;

import com.upc.ViksAdventures.quiz.domain.model.Response;
import com.upc.ViksAdventures.quiz.domain.service.ResponseService;
import com.upc.ViksAdventures.quiz.mapping.ResponseMapper;
import com.upc.ViksAdventures.quiz.resource.CreateResponseResource;
import com.upc.ViksAdventures.quiz.resource.ResponseResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/student-responses", produces = "application/json")
public class ResponseController {
    private final ResponseService responseService;
    private final ResponseMapper mapper;

    public ResponseController(ResponseService responseService, ResponseMapper mapper) {
        this.responseService = responseService;
        this.mapper = mapper;
    }

    // Obtener todas las respuestas de estudiantes
    @GetMapping
    public List<ResponseResource> getAllStudentResponses() {
        return mapper.toResourceList(responseService.getAll());
    }

    // Obtener una respuesta de estudiante por su id
    @GetMapping("/{id}")
    public ResponseResource getStudentResponseById(@PathVariable Long id) {
        return mapper.toResource(responseService.getBydId(id));
    }

    // Crear una nueva respuesta de estudiante
    @PostMapping
    public ResponseResource createStudentResponse(@RequestBody CreateResponseResource resource) {
        Response response = mapper.toModel(resource);
        return mapper.toResource(responseService.create(response));
    }

    // Eliminar una respuesta de estudiante
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentResponse(@PathVariable Long id) {
        return responseService.delete(id);
    }
}

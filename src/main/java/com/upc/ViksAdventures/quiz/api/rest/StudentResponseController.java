package com.upc.ViksAdventures.quiz.api.rest;

import com.upc.ViksAdventures.quiz.domain.model.StudentResponse;
import com.upc.ViksAdventures.quiz.domain.service.StudentResponseService;
import com.upc.ViksAdventures.quiz.mapping.StudentResponseMapper;
import com.upc.ViksAdventures.quiz.resource.CreateStudentResponseResource;
import com.upc.ViksAdventures.quiz.resource.StudentResponseResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/student-responses", produces = "application/json")
public class StudentResponseController {
    private final StudentResponseService studentResponseService;
    private final StudentResponseMapper mapper;

    public StudentResponseController(StudentResponseService studentResponseService, StudentResponseMapper mapper) {
        this.studentResponseService = studentResponseService;
        this.mapper = mapper;
    }

    // Obtener todas las respuestas de estudiantes
    @GetMapping
    public List<StudentResponseResource> getAllStudentResponses() {
        return mapper.toResourceList(studentResponseService.getAll());
    }

    // Obtener una respuesta de estudiante por su id
    @GetMapping("/{id}")
    public StudentResponseResource getStudentResponseById(@PathVariable Long id) {
        return mapper.toResource(studentResponseService.getBydId(id));
    }

    // Crear una nueva respuesta de estudiante
    @PostMapping
    public StudentResponseResource createStudentResponse(@RequestBody CreateStudentResponseResource resource) {
        StudentResponse studentResponse = mapper.toModel(resource);
        return mapper.toResource(studentResponseService.create(studentResponse));
    }

    // Eliminar una respuesta de estudiante
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentResponse(@PathVariable Long id) {
        return studentResponseService.delete(id);
    }
}

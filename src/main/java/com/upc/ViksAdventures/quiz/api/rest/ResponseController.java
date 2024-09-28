package com.upc.ViksAdventures.quiz.api.rest;

import com.upc.ViksAdventures.quiz.domain.model.Option;
import com.upc.ViksAdventures.quiz.domain.model.Response;
import com.upc.ViksAdventures.quiz.domain.model.Student;
import com.upc.ViksAdventures.quiz.domain.service.OptionService;
import com.upc.ViksAdventures.quiz.domain.service.ResponseService;
import com.upc.ViksAdventures.quiz.domain.service.StudentService;
import com.upc.ViksAdventures.quiz.mapping.ResponseMapper;
import com.upc.ViksAdventures.quiz.resource.CreateResponseResource;
import com.upc.ViksAdventures.quiz.resource.ResponseResource;
import com.upc.ViksAdventures.shared.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/responses", produces = "application/json")
public class ResponseController {
    private final ResponseService responseService;
    private final StudentService studentService;
    private final OptionService optionService;
    private final ResponseMapper mapper;

    @Autowired
    public ResponseController(ResponseService responseService,
                              StudentService studentService,
                              OptionService optionService,
                              ResponseMapper mapper) {
        this.responseService = responseService;
        this.studentService = studentService;
        this.optionService = optionService;
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
    public ResponseEntity<ResponseResource> createStudentResponse(@RequestBody @Valid CreateResponseResource resource) {
        Student student = studentService.getBydId(resource.getStudentId());
        Option option = optionService.getBydId(resource.getOptionId());
        Response response = new Response();
        response.setStudent(student);
        response.setOption(option);
        Response savedResponse = responseService.create(response);
        ResponseResource responseResource = mapper.toResource(savedResponse);
        return new ResponseEntity<>(responseResource, HttpStatus.CREATED);
    }

    // Eliminar una respuesta de estudiante
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentResponse(@PathVariable Long id) {
        return responseService.delete(id);
    }
}

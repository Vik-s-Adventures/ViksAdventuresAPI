package com.upc.ViksAdventures.profile.api.rest;

import com.upc.ViksAdventures.profile.domain.model.Student;
import com.upc.ViksAdventures.profile.domain.service.StudentService;
import com.upc.ViksAdventures.profile.mapping.StudentMapper;
import com.upc.ViksAdventures.profile.resource.CreateStudentResource;
import com.upc.ViksAdventures.profile.resource.StudentResource;
import com.upc.ViksAdventures.profile.resource.UpdateStudentResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/students", produces = "application/json")
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper mapper;

    @Autowired
    public StudentController(StudentService studentService, StudentMapper mapper) {
        this.studentService = studentService;
        this.mapper = mapper;
    }

    // Obtener todos los estudiantes
    @GetMapping
    public List<StudentResource> getAllStudents() {
        return mapper.toResourceList(studentService.getAll());
    }

    // Obtener un estudiante por su id
    @GetMapping("/{id}")
    public StudentResource getStudentById(@PathVariable Long id) {
        return mapper.toResource(studentService.getBydId(id));
    }

    // Crear un nuevo estudiante
    @PostMapping
    public StudentResource createStudent(@RequestBody CreateStudentResource resource) {
        Student student = mapper.toModel(resource);
        return mapper.toResource(studentService.create(student));
    }

    // Actualizar un estudiante existente
    @PutMapping("/{id}")
    public StudentResource updateStudent(@PathVariable Long id, @RequestBody UpdateStudentResource resource) {
        Student student = mapper.toModel(resource);
        return mapper.toResource(studentService.update(id, student));
    }

    // Eliminar un estudiante
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        return studentService.delete(id);
    }
}

package com.upc.ViksAdventures.quiz.mapping;

import com.upc.ViksAdventures.quiz.domain.model.Student;
import com.upc.ViksAdventures.quiz.resource.CreateStudentResource;
import com.upc.ViksAdventures.quiz.resource.StudentResource;
import com.upc.ViksAdventures.quiz.resource.UpdateStudentResource;
import com.upc.ViksAdventures.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper implements Serializable {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    @Autowired
    private EnhancedModelMapper mapper;

    public StudentResource toResource(Student model) {
        StudentResource resource = mapper.map(model, StudentResource.class);
        resource.setAge(calculateAge(model.getBirthDate()));
        return resource;
    }

    public Student toModel(CreateStudentResource resource) {
        return mapper.map(resource, Student.class);
    }

    public Student toModel(UpdateStudentResource resource) {
        return mapper.map(resource, Student.class);
    }

    public List<StudentResource> toResourceList(List<Student> modelList) {
        return modelList.stream()
                .map(this::toResource)
                .collect(Collectors.toList());
    }

    public Page<StudentResource> modelListPage(List<Student> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, StudentResource.class), pageable, modelList.size());
    }

    public int calculateAge(String birthDate) {
        try {
            LocalDate dateOfBirth = LocalDate.parse(birthDate, DATE_FORMATTER);  // Usar el formato adecuado
            return Period.between(dateOfBirth, LocalDate.now()).getYears();  // Calcular la edad
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Fecha de nacimiento en formato incorrecto: " + birthDate);
        }
    }
}

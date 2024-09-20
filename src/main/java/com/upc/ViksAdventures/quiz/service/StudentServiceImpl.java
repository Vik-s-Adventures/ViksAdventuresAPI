package com.upc.ViksAdventures.quiz.service;

import com.upc.ViksAdventures.quiz.domain.model.Student;
import com.upc.ViksAdventures.quiz.domain.persistence.StudentRepository;
import com.upc.ViksAdventures.quiz.domain.service.StudentService;
import com.upc.ViksAdventures.shared.exception.ResourceNotFoundException;
import com.upc.ViksAdventures.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {
    private static final String ENTITY = "Student";

    private final StudentRepository studentRepository;
    private final Validator validator;

    public StudentServiceImpl(StudentRepository studentRepository, Validator validator) {
        this.studentRepository = studentRepository;
        this.validator = validator;
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student getBydId(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, studentId));
    }

    @Override
    public Student create(Student student) {
        Set<ConstraintViolation<Student>> violations = validator.validate(student);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        return studentRepository.save(student);
    }

    @Override
    public Student update(Long studentId, Student student) {
        Set<ConstraintViolation<Student>> violations = validator.validate(student);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        return studentRepository.findById(studentId).map(existingStudent -> {
            existingStudent.setFirstName(student.getFirstName());
            existingStudent.setLastName(student.getLastName());
            existingStudent.setGradeLevel(student.getGradeLevel());
            existingStudent.setBirthDate(student.getBirthDate());

            return studentRepository.save(existingStudent);
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, studentId));
    }

    @Override
    public ResponseEntity<?> delete(Long studentId) {
        return studentRepository.findById(studentId).map(student -> {
            studentRepository.delete(student);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, studentId));
    }
}

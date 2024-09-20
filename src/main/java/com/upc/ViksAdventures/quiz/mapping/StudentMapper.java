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
import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    public StudentResource toResource(Student model) {
        return mapper.map(model, StudentResource.class);
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
}

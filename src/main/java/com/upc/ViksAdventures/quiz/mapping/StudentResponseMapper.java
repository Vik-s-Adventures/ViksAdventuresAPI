package com.upc.ViksAdventures.quiz.mapping;

import com.upc.ViksAdventures.quiz.domain.model.StudentResponse;
import com.upc.ViksAdventures.quiz.resource.CreateStudentResponseResource;
import com.upc.ViksAdventures.quiz.resource.StudentResponseResource;
import com.upc.ViksAdventures.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class StudentResponseMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public StudentResponseResource toResource(StudentResponse model) {
        return mapper.map(model, StudentResponseResource.class);
    }

    public StudentResponse toModel(CreateStudentResponseResource resource) {
        return mapper.map(resource, StudentResponse.class);
    }

    public List<StudentResponseResource> toResourceList(List<StudentResponse> modelList) {
        return modelList.stream()
                .map(this::toResource)
                .collect(Collectors.toList());
    }

    public Page<StudentResponseResource> modelListPage(List<StudentResponse> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, StudentResponseResource.class), pageable, modelList.size());
    }
}

package com.upc.ViksAdventures.profile.resource;

import com.upc.ViksAdventures.quiz.domain.model.Response;
import com.upc.ViksAdventures.quiz.domain.model.Result;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentResource {
    private Long id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private int age;
    private String sex;
    private int gradeLevel;
    private String school;
    private String department;
    private String province;
    private String district;
    private Long userId;
    private List<Response> responses;
    private List<Result> results;
}

package com.upc.ViksAdventures.quiz.resource;

import com.upc.ViksAdventures.quiz.domain.model.Response;
import com.upc.ViksAdventures.quiz.domain.model.Result;
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
    private String sex;
    private int gradeLevel;
    private String school;
    private String department;
    private String province;
    private String district;
    private List<Response> responses;
    private List<Result> results;
}

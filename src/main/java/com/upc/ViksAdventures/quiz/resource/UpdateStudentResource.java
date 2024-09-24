package com.upc.ViksAdventures.quiz.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStudentResource {
    @NotNull
    @Size(max = 30)
    private String firstName;

    @NotNull
    @Size(max = 30)
    private String lastName;

    @NotNull
    private String birthDate;

    @NotNull
    private String sex;

    @NotNull
    private int gradeLevel;

    private String school;

    private String department;

    private String province;

    private String district;
}

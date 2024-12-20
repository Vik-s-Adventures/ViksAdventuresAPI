package com.upc.ViksAdventures.profile.resource;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStudentResource {
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

    @NotNull
    private Long userId;

}

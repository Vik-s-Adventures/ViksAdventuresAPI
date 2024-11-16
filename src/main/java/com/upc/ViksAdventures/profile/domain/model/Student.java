package com.upc.ViksAdventures.profile.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.upc.ViksAdventures.first_world.domain.model.Competence;
import com.upc.ViksAdventures.iam.domain.model.User;
import com.upc.ViksAdventures.quiz.domain.model.Response;
import com.upc.ViksAdventures.quiz.domain.model.Result;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private String birthDate;

    @Column(name = "sex", nullable = false)
    private String sex;

    @Column(name = "grade_level", nullable = false)
    private int gradeLevel;

    @Column(name = "school")
    private String school;

    @Column(name="department")
    private String department;

    @Column(name="province")
    private String province;

    @Column(name="district")
    private String district;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Response> responses = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Result> results = new ArrayList<>();
}

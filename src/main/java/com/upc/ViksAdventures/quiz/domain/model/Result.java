package com.upc.ViksAdventures.quiz.domain.model;

import com.upc.ViksAdventures.profile.domain.model.Student;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "score", nullable = false)
    private int score;

    @Column(name = "quiz_id", nullable = false)
    private Long quizId;

    @ManyToOne
    @JoinColumn(name="student_id", nullable = false)
    @JsonBackReference
    private Student student;
}
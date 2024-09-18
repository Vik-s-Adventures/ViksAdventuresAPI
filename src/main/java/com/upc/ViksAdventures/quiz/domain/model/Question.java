package com.upc.ViksAdventures.quiz.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "skill", nullable = false)
    private Skill skill;

    @Column(name = "question_text", nullable = false)
    private String questionText;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false, foreignKey = @ForeignKey(name = "FK_QUIZ_ID"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Quiz quiz;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswerOption> answerOptions = new ArrayList<>();
}
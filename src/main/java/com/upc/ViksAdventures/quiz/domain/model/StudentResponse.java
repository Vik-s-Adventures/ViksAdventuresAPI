package com.upc.ViksAdventures.quiz.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "student_responses")
public class StudentResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quiz_result_id", nullable = false, foreignKey = @ForeignKey(name = "FK_QUIZ_RESULT_ID"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private QuizResult quizResult;

    @ManyToOne
    @JoinColumn(name = "answer_option_id", nullable = false, foreignKey = @ForeignKey(name = "FK_ANSWER_OPTION_ID"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private AnswerOption answerOption;
}
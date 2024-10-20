package com.upc.ViksAdventures.quiz.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Column(name = "performance", nullable = false)
    private Performance performance;

    @Column(name = "question_text", nullable = false)
    private String questionText;

    @Column(name="image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    @JsonBackReference
    private Quiz quiz;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Option> options = new ArrayList<>();
}
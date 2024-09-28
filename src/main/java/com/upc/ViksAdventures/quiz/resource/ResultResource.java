package com.upc.ViksAdventures.quiz.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResultResource {
    private Long id;
    private int score;
    private Long studentId;
}


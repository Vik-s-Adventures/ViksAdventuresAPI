package com.upc.ViksAdventures.quiz.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionResource {
    private Long id;
    private String text;
    private boolean correct;
    private Long questionId;
}

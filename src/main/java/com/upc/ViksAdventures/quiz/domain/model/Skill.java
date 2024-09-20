package com.upc.ViksAdventures.quiz.domain.model;

import lombok.Getter;

@Getter
public enum Skill {
    DESARROLLO_LOGICO(0),
    RAZONAMIENTO_MATEMATICO(1),
    PROBLEMA_MATEMATICO(2);

    private final int value;

    Skill(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return switch (this) {
            case DESARROLLO_LOGICO -> "Desarrollo Lógico";
            case RAZONAMIENTO_MATEMATICO -> "Razonamiento Matemático";
            case PROBLEMA_MATEMATICO -> "Problema Matemático";
            default -> throw new IllegalArgumentException();
        };
    }

    public static Skill fromValue(int value) {
        for (Skill skill : Skill.values()) {
            if (skill.getValue() == value) {
                return skill;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}

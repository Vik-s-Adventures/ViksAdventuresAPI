package com.upc.ViksAdventures.quiz.domain.model;

import lombok.Getter;

@Getter
public enum Performance {
    D1(1),
    D2(2),
    D3(3),
    D4(4),
    D5(5),
    D6(6),
    D7(7),
    D8(8),
    D9(9),
    D10(10);
    private final int value;
    Performance(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
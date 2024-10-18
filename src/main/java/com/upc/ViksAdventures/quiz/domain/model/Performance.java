package com.upc.ViksAdventures.quiz.domain.model;

import lombok.Getter;

@Getter
public enum Performance {
    D1(1, "Expresa su comprensión sobre las fracciones como parte-todo involucrando cantidades discretas desde su representación simbólica hacia su representación gráfica."),
    D2(2, "Expresa su comprensión sobre la fracción como operador involucrando cantidades continuas, a partir de su representación simbólica."),
    D3(3, "Expresa su comprensión sobre las fracciones como medida involucrando cantidades continuas, desde su representación gráfica hacia su representación simbólica."),
    D4(4, "Emplea diversas estrategias para establecer equivalencias entre unidades de masa."),
    D5(5, "Establece relaciones entre los datos y condiciones de situaciones vinculadas al reparto de cantidades. Las transforma a expresiones involucrando los criterios de divisibilidad de números naturales y las resuelve."),
    D6(6, "Establece relaciones entre los datos y condiciones de situaciones vinculadas a las acciones de igualar cantidades involucrando fracciones y las resuelve."),
    D7(7, "Establece relaciones entre datos y condiciones de situaciones para determinar la parte de un total, transformando a expresiones que interpretan el valor del porcentaje."),
    D8(8, "Establece relaciones entre los datos y condiciones de situaciones vinculadas a una combinación de acciones de repetir y separar cantidades, involucrando números decimales."),
    D9(9, "Argumenta la validez de una afirmación vinculada al descuento porcentual de una cantidad en situaciones de su entorno, sustentándola con ejemplos."),
    D10(10, "Emplea diversas estrategias vinculadas al canje para establecer nuevas equivalencias entre cantidades.");

    private final int value;
    private final String description;

    Performance(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}

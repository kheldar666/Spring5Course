package org.libermundi.recipe.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString(of ={"description"},callSuper = true)
public class Ingredient extends Identity {
    private String description;
    private BigDecimal amount;

    private UnitOfMeasure unit;

    private Recipe recipe;

    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure unit) {
        this.description = description;
        this.amount = amount;
        this.unit = unit;
    }

}

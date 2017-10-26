package org.libermundi.recipe.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Data
@ToString(exclude = {"recipe","unit"},callSuper = true)
@EqualsAndHashCode(exclude = {"recipe","unit"},callSuper = true)
@Entity
public class Ingredient extends Identity {
    private String description;
    private BigDecimal amount;

    @OneToOne
    private UnitOfMeasure unit;

    @ManyToOne
    private Recipe recipe;

    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure unit) {
        this.description = description;
        this.amount = amount;
        this.unit = unit;
    }

}

package org.libermundi.recipe.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient extends Identity {
    private String description;
    private BigDecimal amount;

    @OneToOne
    private UnitOfMeasure units;

    @ManyToOne
    private Recipe recipe;

    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure units) {
        this.description = description;
        this.amount = amount;
        this.units = units;
    }

}

package org.libermundi.recipe.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.Set;

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

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure units, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.units = units;
        this.recipe = recipe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public UnitOfMeasure getUnits() {
        return units;
    }

    public void setUnits(UnitOfMeasure units) {
        this.units = units;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipes(Recipe recipe) {
        this.recipe = recipe;
    }
}

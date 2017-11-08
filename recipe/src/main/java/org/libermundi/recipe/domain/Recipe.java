package org.libermundi.recipe.domain;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString(of = {"name"},callSuper = true)
public class Recipe extends Identity{
    private String name;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    private String directions;

    private Difficulty difficulty;

    private Byte[] image;

    private Notes notes;

    private Set<Ingredient> ingredients = new LinkedHashSet<>();

    private Set<Category> categories = new LinkedHashSet<>();

    public Recipe() {
    }

    public Recipe(String name) {
        this.name = name;
    }

    public Recipe addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

}

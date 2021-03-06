package org.libermundi.recipe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@ToString(of = {"name"},callSuper = true)
@Entity
public class Recipe extends Identity{
    private String name;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @Lob
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    @OrderBy
    private Set<Ingredient> ingredients = new LinkedHashSet<>();

    @ManyToMany
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

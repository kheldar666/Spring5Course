package org.libermundi.recipe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@ToString(of = {"name"},callSuper = true)
@EqualsAndHashCode(callSuper = true)
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

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Recipe)) return false;
        final Recipe other = (Recipe) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$prepTime = this.getPrepTime();
        final Object other$prepTime = other.getPrepTime();
        if (this$prepTime == null ? other$prepTime != null : !this$prepTime.equals(other$prepTime)) return false;
        final Object this$cookTime = this.getCookTime();
        final Object other$cookTime = other.getCookTime();
        if (this$cookTime == null ? other$cookTime != null : !this$cookTime.equals(other$cookTime)) return false;
        final Object this$servings = this.getServings();
        final Object other$servings = other.getServings();
        if (this$servings == null ? other$servings != null : !this$servings.equals(other$servings)) return false;
        final Object this$source = this.getSource();
        final Object other$source = other.getSource();
        if (this$source == null ? other$source != null : !this$source.equals(other$source)) return false;
        final Object this$url = this.getUrl();
        final Object other$url = other.getUrl();
        if (this$url == null ? other$url != null : !this$url.equals(other$url)) return false;
        final Object this$directions = this.getDirections();
        final Object other$directions = other.getDirections();
        if (this$directions == null ? other$directions != null : !this$directions.equals(other$directions))
            return false;
        final Object this$difficulty = this.getDifficulty();
        final Object other$difficulty = other.getDifficulty();
        if (this$difficulty == null ? other$difficulty != null : !this$difficulty.equals(other$difficulty))
            return false;
        if (!java.util.Arrays.deepEquals(this.getImage(), other.getImage())) return false;
        final Object this$notes = this.getNotes();
        final Object other$notes = other.getNotes();
        if (this$notes == null ? other$notes != null : !this$notes.equals(other$notes)) return false;
        final Object this$ingredients = this.getIngredients();
        final Object other$ingredients = other.getIngredients();
        if (this$ingredients == null ? other$ingredients != null : !this$ingredients.equals(other$ingredients))
            return false;
        final Object this$categories = this.getCategories();
        final Object other$categories = other.getCategories();
        if (this$categories == null ? other$categories != null : !this$categories.equals(other$categories))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + super.hashCode();
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $prepTime = this.getPrepTime();
        result = result * PRIME + ($prepTime == null ? 43 : $prepTime.hashCode());
        final Object $cookTime = this.getCookTime();
        result = result * PRIME + ($cookTime == null ? 43 : $cookTime.hashCode());
        final Object $servings = this.getServings();
        result = result * PRIME + ($servings == null ? 43 : $servings.hashCode());
        final Object $source = this.getSource();
        result = result * PRIME + ($source == null ? 43 : $source.hashCode());
        final Object $url = this.getUrl();
        result = result * PRIME + ($url == null ? 43 : $url.hashCode());
        final Object $directions = this.getDirections();
        result = result * PRIME + ($directions == null ? 43 : $directions.hashCode());
        final Object $difficulty = this.getDifficulty();
        result = result * PRIME + ($difficulty == null ? 43 : $difficulty.hashCode());
        result = result * PRIME + java.util.Arrays.deepHashCode(this.getImage());
        final Object $notes = this.getNotes();
        result = result * PRIME + ($notes == null ? 43 : $notes.hashCode());
        final Object $ingredients = this.getIngredients();
        result = result * PRIME + ($ingredients == null ? 43 : $ingredients.hashCode());
        final Object $categories = this.getCategories();
        result = result * PRIME + ($categories == null ? 43 : $categories.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Recipe;
    }
}

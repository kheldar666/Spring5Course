package org.libermundi.recipe.services;

import org.libermundi.recipe.domain.Recipe;

public interface RecipeService {
    Iterable<Recipe> getAllRecipes();
}

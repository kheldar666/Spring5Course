package org.libermundi.recipe.services;

import org.libermundi.recipe.commands.RecipeCommand;
import org.libermundi.recipe.domain.Recipe;

public interface RecipeService {
    Iterable<Recipe> getAllRecipes();
    RecipeCommand findById(String id);
    RecipeCommand saveRecipe(RecipeCommand recipeCommand);
    void deleteById(String id);
}

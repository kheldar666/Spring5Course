package org.libermundi.recipe.services;

import org.libermundi.recipe.commands.RecipeCommand;
import org.libermundi.recipe.domain.Recipe;

public interface RecipeService {
    Iterable<Recipe> getAllRecipes();
    Recipe findById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
    void deleteById(Long id);
}

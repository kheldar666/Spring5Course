package org.libermundi.recipe.services;

import org.libermundi.recipe.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findIngredient(String recipeId, String ingredientId);

    IngredientCommand saveIngredient(String recipeId , IngredientCommand ingredient);

    void deleteIngredient(String recipeId, String ingredientId);
}

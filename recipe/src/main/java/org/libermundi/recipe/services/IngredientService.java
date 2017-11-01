package org.libermundi.recipe.services;

import org.libermundi.recipe.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findIngredient(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredient(Long recipeId , IngredientCommand ingredient);
}

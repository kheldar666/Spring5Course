package org.libermundi.recipe.services;

import lombok.extern.slf4j.Slf4j;
import org.libermundi.recipe.commands.IngredientCommand;
import org.libermundi.recipe.commands.RecipeCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    private RecipeService recipeService;

    @Autowired
    public IngredientServiceImpl(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    public IngredientCommand findIngredient(Long recipeId, Long ingredientId) {
        RecipeCommand recipeCommand = recipeService.findById(recipeId);

        Optional<IngredientCommand> ingredientCommand = recipeCommand.getIngredients().stream().filter(
                ingredient -> ingredient.getId().equals(ingredientId))
                .findFirst();

        if(!ingredientCommand.isPresent()) {
            log.error("Ingredient Not Found !");
        }

        return ingredientCommand.get();

    }
}

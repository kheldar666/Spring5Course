package org.libermundi.recipe.services;

import lombok.extern.slf4j.Slf4j;
import org.libermundi.recipe.commands.IngredientCommand;
import org.libermundi.recipe.commands.RecipeCommand;
import org.libermundi.recipe.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    private RecipeService recipeService;

    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(RecipeService recipeService, IngredientRepository ingredientRepository) {
        this.recipeService = recipeService;
        this.ingredientRepository = ingredientRepository;
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

    @Override
    @Transactional
    public IngredientCommand saveIngredient(Long recipeId, IngredientCommand ingredient) {
        RecipeCommand recipe = recipeService.findById(recipeId);

        recipe.getIngredients().add(ingredient);

        recipeService.saveRecipe(recipe);

        return ingredient;
    }


    @Override
    @Transactional
    public void deleteIngredient(Long recipeId, Long id) {
        ingredientRepository.deleteByIdAndRecipeId(id,recipeId);
    }
}

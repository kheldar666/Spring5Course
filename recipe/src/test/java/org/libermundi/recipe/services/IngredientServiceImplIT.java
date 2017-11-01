package org.libermundi.recipe.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.libermundi.recipe.commands.IngredientCommand;
import org.libermundi.recipe.domain.Ingredient;
import org.libermundi.recipe.domain.Recipe;
import org.libermundi.recipe.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredientServiceImplIT {

    @Autowired
    IngredientService ingredientService;

    @Autowired
    RecipeRepository recipeRepository;

    @Test
    @Transactional
    public void findIngredient() throws Exception {
        // Given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        Ingredient testIngredient = testRecipe.getIngredients().iterator().next();

        // When
        IngredientCommand searchedIngredient = ingredientService.findIngredient(testRecipe.getId(),testIngredient.getId());

        // Then
        assertEquals(testIngredient.getId(),searchedIngredient.getId());

    }

}
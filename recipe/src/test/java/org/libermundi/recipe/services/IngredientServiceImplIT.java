package org.libermundi.recipe.services;

import com.google.common.collect.Iterables;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.libermundi.recipe.commands.IngredientCommand;
import org.libermundi.recipe.domain.Ingredient;
import org.libermundi.recipe.domain.Recipe;
import org.libermundi.recipe.domain.UnitOfMeasure;
import org.libermundi.recipe.repositories.IngredientRepository;
import org.libermundi.recipe.repositories.RecipeRepository;
import org.libermundi.recipe.repositories.UnitOfMeasureRepository;
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

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    IngredientRepository ingredientRepository;

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

    @Test
    @Transactional
    public void saveIngredient() throws Exception {
        // Given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        Ingredient testIngredient = testRecipe.getIngredients().iterator().next();

        Iterable<UnitOfMeasure> uoms = unitOfMeasureRepository.findAll();
        uoms.forEach(unitOfMeasure -> {
            if (!unitOfMeasure.getId().equals(testIngredient.getUnit().getId())) {
                testIngredient.setUnit(unitOfMeasure);
                return;
            }});

        // When


        // Then
    }

    @Test
    @Transactional
    public void deleteIngredient() throws Exception {
        // Given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        Ingredient testIngredient = testRecipe.getIngredients().iterator().next();

        // When
        testRecipe.getIngredients().remove(testIngredient); //Necesary due to the relation between Recipe and Ingredient in the Transaction
        ingredientService.deleteIngredient(testRecipe.getId(),testIngredient.getId());

        // Then
        Recipe updated = recipeRepository.findById(testRecipe.getId()).get();
        assertFalse(updated.getIngredients().contains(testIngredient));
    }
}
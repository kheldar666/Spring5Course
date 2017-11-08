package org.libermundi.recipe.services;

import com.google.common.collect.Iterables;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.libermundi.recipe.commands.IngredientCommand;
import org.libermundi.recipe.commands.RecipeCommand;
import org.libermundi.recipe.converters.IngredientToIngredientCommand;
import org.libermundi.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
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

import java.math.BigDecimal;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredientServiceImplIT {
    private static final String UPDATED_DESCRIPTION="UPDATED INGREDIENT";

    private static final String ADDED_DESCRIPTION="UPDATED INGREDIENT";
    private static final BigDecimal ADDED_AMOUNT=new BigDecimal(10);

    @Autowired
    IngredientService ingredientService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    IngredientToIngredientCommand ingredientToIngredientCommand;

    @Autowired
    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

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
    public void updateIngredient() throws Exception {
        // Given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        Ingredient testIngredient = testRecipe.getIngredients().iterator().next();

        UnitOfMeasure testUom = testIngredient.getUnit();


        testIngredient.setDescription(UPDATED_DESCRIPTION);
        Iterable<UnitOfMeasure> uoms = unitOfMeasureRepository.findAll();
        for (UnitOfMeasure uom : uoms) {
            if (!uom.getId().equals(testIngredient.getUnit().getId())) {
                testIngredient.setUnit(uom);
                break;
            }
        };


        String ingredientId = testIngredient.getId();
        String recipeId = testRecipe.getId();


        // When
        ingredientService.saveIngredient(recipeId, ingredientToIngredientCommand.convert(testIngredient));

        // Then
        Recipe updatedRecipe = recipeRepository.findById(recipeId).get();

        Ingredient updatedIngredient = updatedRecipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId)).findFirst().get();

        assertTrue(updatedIngredient.getDescription().equals(UPDATED_DESCRIPTION));
        assertTrue(!testUom.equals(updatedIngredient.getUnit()));
    }

    @Test
    @Transactional
    public void addIngredient() {
        // Given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();

        int nbreIngredients = testRecipe.getIngredients().size();

        IngredientCommand ingredient = new IngredientCommand();
            ingredient.setDescription(ADDED_DESCRIPTION);
            ingredient.setAmount(ADDED_AMOUNT);
            ingredient.setUnit(
                    unitOfMeasureToUnitOfMeasureCommand.convert(
                            unitOfMeasureRepository.findAll().iterator().next()
                    ));

        // When
        ingredientService.saveIngredient(testRecipe.getId(),ingredient);

        // Then
        Recipe updatedRecipe = recipeRepository.findById(testRecipe.getId()).get();
        assertTrue(updatedRecipe.getIngredients().size() == nbreIngredients +1 );
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
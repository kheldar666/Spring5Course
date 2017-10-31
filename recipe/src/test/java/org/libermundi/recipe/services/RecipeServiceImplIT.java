package org.libermundi.recipe.services;

import com.google.common.collect.Iterables;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.libermundi.recipe.commands.RecipeCommand;
import org.libermundi.recipe.converters.RecipeCommandToRecipe;
import org.libermundi.recipe.converters.RecipeToRecipeCommand;
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
public class RecipeServiceImplIT {
    private static final String NEW_RECIPE_NAME ="This is an updated Recipe";
    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Test
    @Transactional
    public void testSaveOfDescription() throws Exception {
        // Given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(testRecipe);

        // When
        recipeCommand.setName(NEW_RECIPE_NAME);
        RecipeCommand savedRecipe = recipeService.saveRecipe(recipeCommand);

        // Then
        assertEquals(NEW_RECIPE_NAME, savedRecipe.getName());
        assertEquals(testRecipe.getId(), savedRecipe.getId());
        assertEquals(testRecipe.getCategories().size(), savedRecipe.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), savedRecipe.getIngredients().size());
        assertEquals(testRecipe.getCreated(),savedRecipe.getCreated());
        assertEquals(testRecipe.getUpdated(), savedRecipe.getUpdated());

    }

    @Test
    public void testDeleteARecipe() throws Exception {
        // Given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe deletedRecipe = recipes.iterator().next();

        log.info("Recipe to Delete : " + deletedRecipe);


        // When
        recipeService.deleteById(deletedRecipe.getId());

        // Then
        Iterable<Recipe> recipes2 = recipeRepository.findAll();
        recipes2.forEach(recipe -> assertFalse(recipe.equals(deletedRecipe)));
        assertTrue(Iterables.size(recipes) == Iterables.size(recipes2)+1);
    }

}
package org.libermundi.recipe.services;

import com.sun.org.apache.bcel.internal.generic.NEW;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
        RecipeCommand savedRecipe = recipeService.saveRecipeCommand(recipeCommand);

        // Then
        assertEquals(NEW_RECIPE_NAME, savedRecipe.getName());
        assertEquals(testRecipe.getId(), savedRecipe.getId());
        assertEquals(testRecipe.getCategories().size(), savedRecipe.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), savedRecipe.getIngredients().size());
        assertEquals(testRecipe.getCreated(),savedRecipe.getCreated());
        assertEquals(testRecipe.getUpdated(), savedRecipe.getUpdated());

    }

}
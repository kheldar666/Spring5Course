package org.libermundi.recipe.services;

import com.google.common.collect.Iterables;
import org.junit.Before;
import org.junit.Test;
import org.libermundi.recipe.commands.RecipeCommand;
import org.libermundi.recipe.converters.RecipeCommandToRecipe;
import org.libermundi.recipe.converters.RecipeToRecipeCommand;
import org.libermundi.recipe.domain.Recipe;
import org.libermundi.recipe.repositories.RecipeRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {
    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository,recipeCommandToRecipe,recipeToRecipeCommand);
    }

    @Test
    public void getAllRecipes() throws Exception {
        Recipe recipe = new Recipe();

        HashSet recipeData = new HashSet();

        recipeData.add(recipe);

        when(recipeService.getAllRecipes()).thenReturn(recipeData);

        Iterable<Recipe> recipes = recipeService.getAllRecipes();

        assertEquals(Iterables.size(recipes),1);

        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void findById() {
        // Given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        RecipeCommand command = new RecipeCommand();
        command.setId(1L);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeToRecipeCommand.convert(any(Recipe.class))).thenReturn(command);

        // When
        RecipeCommand retrievedRecipe = recipeService.findById(1L);

        // Then
        assertEquals(recipe.getId(), retrievedRecipe.getId());

        verify(recipeRepository,times(1)).findById(anyLong());
    }

}
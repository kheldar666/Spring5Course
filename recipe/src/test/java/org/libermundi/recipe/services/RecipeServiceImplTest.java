package org.libermundi.recipe.services;

import com.google.common.collect.Iterables;
import org.junit.Before;
import org.junit.Test;
import org.libermundi.recipe.commands.RecipeCommand;
import org.libermundi.recipe.converters.RecipeCommandToRecipe;
import org.libermundi.recipe.converters.RecipeToRecipeCommand;
import org.libermundi.recipe.domain.Recipe;
import org.libermundi.recipe.exceptions.NotFoundException;
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
        recipe.setId("1234567879");

        RecipeCommand command = new RecipeCommand();
        command.setId("1234567879");

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);
        when(recipeToRecipeCommand.convert(any(Recipe.class))).thenReturn(command);

        // When
        RecipeCommand retrievedRecipe = recipeService.findById("1234567879");

        // Then
        assertEquals(recipe.getId(), retrievedRecipe.getId());

        verify(recipeRepository,times(1)).findById(anyString());
    }

    @Test( expected = NotFoundException.class )
    public void findByIdNotFound() {
        // Given
        Optional<Recipe> recipeOptional = Optional.empty();

        // When
        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

        // Then
        RecipeCommand recipe = recipeService.findById("1234567879");
    }

}
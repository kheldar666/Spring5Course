package org.libermundi.recipe.services;

import org.junit.Before;
import org.junit.Test;
import org.libermundi.recipe.commands.IngredientCommand;
import org.libermundi.recipe.commands.RecipeCommand;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {
    IngredientService ingredientService;

    @Mock
    RecipeService recipeService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(recipeService);
    }

    @Test
    public void findIngredient() throws Exception {
        // Given
        IngredientCommand ingredient = new IngredientCommand();
        ingredient.setId(7L);

        RecipeCommand recipe = new RecipeCommand();
        recipe.setId(1L);
        recipe.getIngredients().add(ingredient);

        when(recipeService.findById(1L)).thenReturn(recipe);

        // When
        IngredientCommand found = ingredientService.findIngredient(1L,7L);


        // Then
        verify(recipeService,times(1)).findById(anyLong());
        assertEquals(ingredient.getId(), found.getId());
    }

}
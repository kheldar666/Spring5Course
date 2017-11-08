package org.libermundi.recipe.services;

import org.junit.Before;
import org.junit.Test;
import org.libermundi.recipe.commands.IngredientCommand;
import org.libermundi.recipe.commands.RecipeCommand;
import org.libermundi.recipe.repositories.IngredientRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {
    IngredientService ingredientService;

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientRepository ingredientRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(recipeService,ingredientRepository);
    }

    @Test
    public void findIngredient() throws Exception {
        // Given
        IngredientCommand ingredient = new IngredientCommand();
        ingredient.setId("35161615651");

        RecipeCommand recipe = new RecipeCommand();
        recipe.setId("61651651651");
        recipe.getIngredients().add(ingredient);

        when(recipeService.findById("61651651651")).thenReturn(recipe);

        // When
        IngredientCommand found = ingredientService.findIngredient("61651651651","35161615651");


        // Then
        verify(recipeService,times(1)).findById(anyString());
        assertEquals(ingredient.getId(), found.getId());
    }


}
package org.libermundi.recipe.services;

import com.google.common.collect.Iterables;
import org.junit.Before;
import org.junit.Test;
import org.libermundi.recipe.domain.Recipe;
import org.libermundi.recipe.repositories.RecipeRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RecipeServiceImplTest {
    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
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

}
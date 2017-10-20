package org.libermundi.recipe.controllers;

import org.junit.Before;
import org.junit.Test;
import org.libermundi.recipe.domain.Recipe;
import org.libermundi.recipe.services.RecipeService;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RecipeControllerTest {
    RecipeController recipeController;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);
    }


    @Test
    public void testMvc() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        mockMvc.perform(get("/recipe/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/show"));
    }

    @Test
    public void show() throws Exception {
        // Given
        Recipe recipe = new Recipe("Test Recipe");
        recipe.setId(1L);

        when(recipeService.findById(anyLong())).thenReturn(recipe);

        ArgumentCaptor<Recipe> argumentCaptor= ArgumentCaptor.forClass(Recipe.class);

        // When
        String viewName = recipeController.show(model, 1L);

        // Then
        assertEquals("/recipe/show", viewName);
        verify(recipeService, times(1)).findById(anyLong());
        verify(model, times(1)).addAttribute(eq("recipe"), argumentCaptor.capture());

        assertEquals("Test Recipe", recipe.getName());
    }

}
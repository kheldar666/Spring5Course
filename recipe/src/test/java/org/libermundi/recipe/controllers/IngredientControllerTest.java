package org.libermundi.recipe.controllers;

import org.junit.Before;
import org.junit.Test;
import org.libermundi.recipe.commands.IngredientCommand;
import org.libermundi.recipe.commands.RecipeCommand;
import org.libermundi.recipe.domain.Recipe;
import org.libermundi.recipe.services.IngredientService;
import org.libermundi.recipe.services.RecipeService;
import org.libermundi.recipe.services.UnitOfMeasureService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IngredientControllerTest {

    IngredientController ingredientController;

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientController = new IngredientController(recipeService,ingredientService,unitOfMeasureService);

        when(recipeService.findById(1L)).thenReturn(new RecipeCommand());
        when(ingredientService.findIngredient(1L,1L)).thenReturn(new IngredientCommand());
    }

    @Test
    public void list() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();

        mockMvc.perform(get("/recipe/1/ingredients/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/ingredients/list"))
                .andExpect(model().attributeExists("recipe"));

    }

    @Test
    public void edit() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();

        mockMvc.perform(get("/recipe/1/ingredients/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/ingredients/edit"))
                .andExpect(model().attributeExists("ingredient"));

    }

}
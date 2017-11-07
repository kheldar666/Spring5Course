package org.libermundi.recipe.controllers;

import org.junit.Before;
import org.junit.Test;
import org.libermundi.recipe.commands.RecipeCommand;
import org.libermundi.recipe.converters.*;
import org.libermundi.recipe.domain.Recipe;
import org.libermundi.recipe.exceptions.NotFoundException;
import org.libermundi.recipe.services.RecipeService;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RecipeControllerTest {
    RecipeController recipeController;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    RecipeCommand recipe;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);

        recipe = new RecipeCommand();
        recipe.setName("Test Recipe");
        recipe.setId(1L);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        when(recipeService.findById(anyLong())).thenReturn(recipe);
    }


    @Test
    public void testGetShowView() throws Exception {
        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void testGetNewView() throws Exception {
        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/form"))
                .andExpect(model().attributeExists("recipe"));
    }


    @Test
    public void testGetUpdateView() throws Exception {
        mockMvc.perform(get("/recipe/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/form"))
                .andExpect(model().attributeExists("recipe"))
                .andExpect(model().attributeExists("cancelUrl"));
    }


    @Test
    public void testGetDeleteView() throws Exception {
        mockMvc.perform(get("/recipe/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    public void show() throws Exception {
        // Given
        ArgumentCaptor<Recipe> argumentCaptor= ArgumentCaptor.forClass(Recipe.class);

        // When
        String viewName = recipeController.show(model, 1L);

        // Then
        assertEquals("recipe/show", viewName);
        verify(recipeService, times(1)).findById(anyLong());
        verify(model, times(1)).addAttribute(eq("recipe"), argumentCaptor.capture());

        assertEquals("Test Recipe", recipe.getName());
    }

    @Test
    public void edit() throws Exception {
        // Given
        ArgumentCaptor<RecipeCommand> argumentCaptor= ArgumentCaptor.forClass(RecipeCommand.class);
        ArgumentCaptor<String> argumentCaptor2= ArgumentCaptor.forClass(String.class);

        // When
        String viewName = recipeController.edit(model, 1L);

        //Ten
        assertEquals("recipe/form", viewName);
        verify(recipeService, times(1)).findById(anyLong());
        verify(model, times(1)).addAttribute(eq("recipe"), argumentCaptor.capture());
        verify(model, times(1)).addAttribute(eq("cancelUrl"), argumentCaptor2.capture());

        assertEquals("Test Recipe", recipe.getName());
        assertEquals("/recipe/1/show",argumentCaptor2.getValue());

    }

    @Test
    public void delete() throws Exception {
        // When
        String viewName = recipeController.delete(1L);

        // Then
        assertEquals("redirect:/", viewName);
        verify(recipeService, times(1)).deleteById(anyLong());
    }


    @Test
    public void findByIdNotFound() throws Exception {
        // When
        when(recipeService.findById(anyLong())).thenThrow(NotFoundException.class);

        // Then
        mockMvc.perform(get("/recipe/4/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("errors/404"));
    }

    @Test
    public void testGetRecipeNumberFormatException() throws Exception {

        mockMvc.perform(get("/recipe/asdf/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("errors/400"));
    }
}
package org.libermundi.recipe.controllers;

import com.google.common.collect.Iterables;
import org.junit.Before;
import org.junit.Test;
import org.libermundi.recipe.domain.Recipe;
import org.libermundi.recipe.services.RecipeService;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    IndexController indexController;

    @Mock
    private RecipeService recipeService;

    @Mock
    private Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() throws Exception {
        //Given
        ArrayList<Recipe> recipes = new ArrayList<>();

        recipes.add(new Recipe());
        recipes.add(new Recipe());

        when(recipeService.getAllRecipes()).thenReturn(recipes);

        ArgumentCaptor<Iterable<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Iterable.class);

        //When
        String viewName = indexController.getIndexPage(model);

        //Then
        assertEquals("index",viewName);
        verify(recipeService, times(1)).getAllRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());

        Iterable<Recipe> iterableInController = argumentCaptor.getValue();

        assertEquals(2, Iterables.size(iterableInController));

    }

}
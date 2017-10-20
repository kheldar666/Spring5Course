package org.libermundi.recipe.converters;

import org.junit.Before;
import org.junit.Test;
import org.libermundi.recipe.commands.CategoryCommand;
import org.libermundi.recipe.commands.IngredientCommand;
import org.libermundi.recipe.commands.NotesCommand;
import org.libermundi.recipe.commands.RecipeCommand;
import org.libermundi.recipe.domain.*;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {
    public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String NAME = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID_2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;

    RecipeCommandToRecipe converter;

    RecipeCommand recipeCommand;

    UnitOfMeasureCommandToUnitOfMeasure measureConverter = new UnitOfMeasureCommandToUnitOfMeasure();

    IngredientCommandToIngredient ingredientConverter = new IngredientCommandToIngredient(measureConverter);

    CategoryCommandToCategory categoryConverter = new CategoryCommandToCategory();

    NotesCommandToNotes notesConverter = new NotesCommandToNotes();

    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(ingredientConverter,categoryConverter,notesConverter);
    }

    @Test
    public void convertNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void convertEmpty() throws Exception {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void convert() throws Exception {
        // Given
        CategoryCommand cat1 = new CategoryCommand();
            cat1.setId(CAT_ID_1);

        CategoryCommand cat2 = new CategoryCommand();
            cat2.setId(CAT_ID_2);

        IngredientCommand ing1 = new IngredientCommand();
            ing1.setId(INGRED_ID_1);

        IngredientCommand ing2 = new IngredientCommand();
            ing2.setId(INGRED_ID_2);

        NotesCommand notes = new NotesCommand();
            notes.setId(NOTES_ID);

        recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setName(NAME);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
        recipeCommand.getCategories().add(cat1);
        recipeCommand.getCategories().add(cat2);
        recipeCommand.getIngredients().add(ing1);
        recipeCommand.getIngredients().add(ing2);
        recipeCommand.setNotes(notes);

        // When
        Recipe recipe = converter.convert(recipeCommand);

        // Then
        assertNotNull(recipe);
        assertEquals(RECIPE_ID,recipe.getId());
        assertEquals(COOK_TIME,recipe.getCookTime());
        assertEquals(PREP_TIME,recipe.getPrepTime());
        assertEquals(NAME,recipe.getName());
        assertEquals(DIRECTIONS,recipe.getDirections());
        assertEquals(DIFFICULTY,recipe.getDifficulty());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE,recipe.getSource());
        assertEquals(URL,recipe.getUrl());
        assertEquals(2, recipe.getIngredients().size());
        assertEquals(2, recipe.getCategories().size());
    }

}
package org.libermundi.recipe.converters;

import lombok.Synchronized;
import org.libermundi.recipe.commands.CategoryCommand;
import org.libermundi.recipe.commands.IngredientCommand;
import org.libermundi.recipe.commands.RecipeCommand;
import org.libermundi.recipe.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class RecipeCommandToRecipe extends IdentityCommandToIdentity implements Converter<RecipeCommand, Recipe> {

    IngredientCommandToIngredient ingredientConverter;

    CategoryCommandToCategory categoryConverter;

    NotesCommandToNotes notesConverter;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientConverter
            , CategoryCommandToCategory categoryConverter, NotesCommandToNotes notesConverter) {
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
        this.notesConverter = notesConverter;
    }

    @Nullable
    @Override
    @Synchronized
    public Recipe convert(RecipeCommand recipeCommand) {
        if (recipeCommand == null) {
            return null;
        }

        Recipe recipe = new Recipe();

        convertIdentityCommand(recipeCommand,recipe);

        recipe.setName(recipeCommand.getName());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setServings(recipeCommand.getServings());
        recipe.setSource(recipeCommand.getSource());
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setDirections(recipeCommand.getDirections());

        for (Iterator<IngredientCommand> iterator = recipeCommand.getIngredients().iterator(); iterator.hasNext(); ) {
            IngredientCommand ingredientCommand = iterator.next();
            recipe.getIngredients().add(ingredientConverter.convert(ingredientCommand));
        }

        recipe.setDifficulty(recipeCommand.getDifficulty());

        for (Iterator<CategoryCommand> iterator = recipeCommand.getCategories().iterator(); iterator.hasNext(); ) {
            CategoryCommand categoryCommand = iterator.next();
            recipe.getCategories().add(categoryConverter.convert(categoryCommand));
        }

        recipe.setNotes(notesConverter.convert(recipeCommand.getNotes()));

        return recipe;
    }
}

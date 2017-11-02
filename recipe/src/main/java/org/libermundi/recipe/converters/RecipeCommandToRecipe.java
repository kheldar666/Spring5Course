package org.libermundi.recipe.converters;

import lombok.Synchronized;
import org.libermundi.recipe.commands.CategoryCommand;
import org.libermundi.recipe.commands.IngredientCommand;
import org.libermundi.recipe.commands.RecipeCommand;
import org.libermundi.recipe.domain.Recipe;
import org.libermundi.recipe.repositories.RecipeRepository;
import org.libermundi.recipe.utils.NullAwareBeanUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    IngredientCommandToIngredient ingredientConverter;

    CategoryCommandToCategory categoryConverter;

    NotesCommandToNotes notesConverter;

    RecipeRepository recipeRepository;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientConverter, CategoryCommandToCategory categoryConverter, NotesCommandToNotes notesConverter, RecipeRepository recipeRepository) {
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
        this.notesConverter = notesConverter;
        this.recipeRepository = recipeRepository;
    }

    @Nullable
    @Override
    @Synchronized
    public Recipe convert(RecipeCommand recipeCommand) {
        if (recipeCommand == null) {
            return null;
        }

        Recipe recipe;

        if(recipeCommand.getId() != null) {
            recipe = recipeRepository.findById(recipeCommand.getId()).get();
        } else {
            recipe = new Recipe();
        }

        NullAwareBeanUtil.copyProperties(recipeCommand,recipe, "notes");

        for (Iterator<IngredientCommand> iterator = recipeCommand.getIngredients().iterator(); iterator.hasNext(); ) {
            IngredientCommand ingredientCommand = iterator.next();
            recipe.getIngredients().add(ingredientConverter.convert(ingredientCommand));
        }


        for (Iterator<CategoryCommand> iterator = recipeCommand.getCategories().iterator(); iterator.hasNext(); ) {
            CategoryCommand categoryCommand = iterator.next();
            recipe.getCategories().add(categoryConverter.convert(categoryCommand));
        }

        recipe.setNotes(notesConverter.convert(recipeCommand.getNotes()));

        return recipe;
    }
}

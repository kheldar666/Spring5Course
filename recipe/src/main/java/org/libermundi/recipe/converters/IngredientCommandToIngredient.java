package org.libermundi.recipe.converters;

import lombok.Synchronized;
import org.libermundi.recipe.commands.IngredientCommand;
import org.libermundi.recipe.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

public class IngredientCommandToIngredient implements Converter<IngredientCommand,Ingredient> {

    private UnitOfMeasureCommandToUnitOfMeasure unitConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure unitConverter) {
        this.unitConverter = unitConverter;
    }

    @Nullable
    @Override
    @Synchronized
    public Ingredient convert(IngredientCommand ingredientCommand) {
        if(ingredientCommand == null) {
            return null;
        }

        Ingredient ingredient = new Ingredient();

        ingredient.setId(ingredientCommand.getId());
        ingredient.setDescription(ingredientCommand.getDescription());
        ingredient.setAmount(ingredientCommand.getAmount());
        ingredient.setUnit(unitConverter.convert(ingredientCommand.getUnit()));

        return ingredient;
    }
}

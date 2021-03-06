package org.libermundi.recipe.converters;

import lombok.Synchronized;
import org.libermundi.recipe.commands.IngredientCommand;
import org.libermundi.recipe.domain.Ingredient;
import org.libermundi.recipe.utils.NullAwareBeanUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 6/21/17.
 */
@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }

        IngredientCommand ingredientCommand = new IngredientCommand();

        NullAwareBeanUtil.copyProperties(ingredient,ingredientCommand,"unit");
        ingredientCommand.setUnit(uomConverter.convert(ingredient.getUnit()));
        return ingredientCommand;
    }
}

package org.libermundi.recipe.converters;

import lombok.Synchronized;
import org.libermundi.recipe.commands.IngredientCommand;
import org.libermundi.recipe.domain.Ingredient;
import org.libermundi.recipe.repositories.IngredientRepository;
import org.libermundi.recipe.utils.NullAwareBeanUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand,Ingredient> {

    private UnitOfMeasureCommandToUnitOfMeasure unitConverter;

    private IngredientRepository ingredientRepository;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure unitConverter,
                                         IngredientRepository ingredientRepository) {
        this.unitConverter = unitConverter;
        this.ingredientRepository = ingredientRepository;
    }

    @Nullable
    @Override
    @Synchronized
    public Ingredient convert(IngredientCommand ingredientCommand) {
        if(ingredientCommand == null) {
            return null;
        }

        Ingredient ingredient;

        if(ingredientCommand.getId() != null) {
            ingredient = ingredientRepository.findById(ingredientCommand.getId()).get();
        } else {
            ingredient = new Ingredient();
        }

        NullAwareBeanUtil.copyProperties(ingredientCommand,ingredient,"unit");

        ingredient.setUnit(unitConverter.convert(ingredientCommand.getUnit()));

        return ingredient;
    }
}

package org.libermundi.recipe.converters;

import org.junit.Before;
import org.junit.Test;
import org.libermundi.recipe.commands.IngredientCommand;
import org.libermundi.recipe.commands.UnitOfMeasureCommand;
import org.libermundi.recipe.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {
    private static final Long ID=1L;
    private static final String DESCRIPTION="Ingredient Description";
    private static final BigDecimal AMOUNT=new BigDecimal(4);
    private static final Long UOM_ID=2L;


    Converter<IngredientCommand,Ingredient> converter;

    IngredientCommand ingredientCommand;

    @Before
    public void setUp(){
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());

        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
                unitOfMeasureCommand.setId(UOM_ID);

        ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setUnit(unitOfMeasureCommand);

    }

    @Test
    public void convertNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void convertEmpty() throws Exception {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void convert() throws Exception {
        Ingredient ingredient = converter.convert(ingredientCommand);
        Ingredient ingredient2 = converter.convert(ingredientCommand);

        assertEquals(ID,ingredient.getId());
        assertEquals(DESCRIPTION,ingredient.getDescription());
        assertEquals(AMOUNT,ingredient.getAmount());
        assertEquals(UOM_ID,ingredient.getUnit().getId());

        assertEquals(ingredient,ingredient2);

    }

}
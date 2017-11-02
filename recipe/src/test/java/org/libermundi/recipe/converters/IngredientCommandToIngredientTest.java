package org.libermundi.recipe.converters;

import org.junit.Before;
import org.junit.Test;
import org.libermundi.recipe.commands.IngredientCommand;
import org.libermundi.recipe.commands.UnitOfMeasureCommand;
import org.libermundi.recipe.domain.Ingredient;
import org.libermundi.recipe.domain.UnitOfMeasure;
import org.libermundi.recipe.repositories.IngredientRepository;
import org.libermundi.recipe.repositories.UnitOfMeasureRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.converter.Converter;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class IngredientCommandToIngredientTest {
    private static final Long ID=1L;
    private static final String DESCRIPTION="Ingredient Description";
    private static final BigDecimal AMOUNT=new BigDecimal(4);
    private static final Long UOM_ID=2L;


    Converter<IngredientCommand,Ingredient> converter;

    IngredientCommand ingredientCommand;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Mock
    IngredientRepository ingredientRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure(unitOfMeasureRepository),ingredientRepository);

        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
                unitOfMeasureCommand.setId(UOM_ID);

        ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setUnit(unitOfMeasureCommand);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID);
        when(ingredientRepository.findById(anyLong())).thenReturn(Optional.of(ingredient));

        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(UOM_ID);
        when(unitOfMeasureRepository.findById(anyLong())).thenReturn(Optional.of(unitOfMeasure));
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
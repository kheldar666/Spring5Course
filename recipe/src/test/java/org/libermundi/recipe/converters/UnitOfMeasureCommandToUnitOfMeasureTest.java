package org.libermundi.recipe.converters;

import org.junit.Before;
import org.junit.Test;
import org.libermundi.recipe.commands.UnitOfMeasureCommand;
import org.libermundi.recipe.domain.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {
    private static final Long ID=1L;
    private static final String NAME="Milliliter";
    private static final String UNIT="ml";

    Converter<UnitOfMeasureCommand, UnitOfMeasure> converter;

    UnitOfMeasureCommand unitOfMeasureCommand;

    UnitOfMeasure unitOfMeasure;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
        unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(ID);
        unitOfMeasureCommand.setName(NAME);
        unitOfMeasureCommand.setUnit(UNIT);
    }

    @Test
    public void convertNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void convertEmpty() throws Exception {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convert() throws Exception {
        unitOfMeasure = converter.convert(unitOfMeasureCommand);

        assertNotNull(unitOfMeasure);
        assertEquals(ID,unitOfMeasure.getId());
        assertEquals(NAME,unitOfMeasure.getName());
        assertEquals(UNIT,unitOfMeasure.getUnit());
    }

}
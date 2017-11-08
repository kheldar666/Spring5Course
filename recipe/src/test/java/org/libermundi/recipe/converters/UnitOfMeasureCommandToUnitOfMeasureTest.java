package org.libermundi.recipe.converters;

import org.junit.Before;
import org.junit.Test;
import org.libermundi.recipe.commands.UnitOfMeasureCommand;
import org.libermundi.recipe.domain.UnitOfMeasure;
import org.libermundi.recipe.repositories.UnitOfMeasureRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UnitOfMeasureCommandToUnitOfMeasureTest {
    private static final String ID="6514651651681";
    private static final String NAME="Milliliter";
    private static final String UNIT="ml";

    Converter<UnitOfMeasureCommand, UnitOfMeasure> converter;

    UnitOfMeasureCommand unitOfMeasureCommand;

    UnitOfMeasure unitOfMeasure;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(ID);
        when(unitOfMeasureRepository.findById(anyString())).thenReturn(Optional.of(uom));


        converter = new UnitOfMeasureCommandToUnitOfMeasure(unitOfMeasureRepository);
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
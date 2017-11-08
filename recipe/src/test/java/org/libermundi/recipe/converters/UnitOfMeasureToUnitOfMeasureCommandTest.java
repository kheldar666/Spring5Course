package org.libermundi.recipe.converters;


import org.junit.Before;
import org.junit.Test;
import org.libermundi.recipe.commands.UnitOfMeasureCommand;
import org.libermundi.recipe.domain.UnitOfMeasure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by jt on 6/21/17.
 */
public class UnitOfMeasureToUnitOfMeasureCommandTest {

    public static final String NAME = "description";
    public static final String LONG_VALUE = "265461315431541";

    UnitOfMeasureToUnitOfMeasureCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullObjectConvert() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObj() throws Exception {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert() throws Exception {
        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(LONG_VALUE);
        uom.setName(NAME);
        //when
        UnitOfMeasureCommand uomc = converter.convert(uom);

        //then
        assertEquals(LONG_VALUE, uomc.getId());
        assertEquals(NAME, uomc.getName());
    }

}
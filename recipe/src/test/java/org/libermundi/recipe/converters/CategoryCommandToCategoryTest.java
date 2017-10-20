package org.libermundi.recipe.converters;

import org.junit.Before;
import org.junit.Test;
import org.libermundi.recipe.commands.CategoryCommand;
import org.libermundi.recipe.domain.Category;
import org.springframework.core.convert.converter.Converter;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {
    private static final Long ID = 1L;
    private static final String NAME = "Category Name";


    Converter<CategoryCommand,Category> converter;

    CategoryCommand categoryCommand;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryCommandToCategory();
        categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID);
        categoryCommand.setName(NAME);
    }

    @Test
    public void convertNull() throws Exception {
        assertNull(converter.convert(null));
    }
    @Test
    public void convertEmpty() throws Exception {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() throws Exception {
        Category cat = converter.convert(categoryCommand);

        assertEquals(ID,cat.getId());
        assertEquals(NAME,cat.getName());
    }

}
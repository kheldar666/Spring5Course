package org.libermundi.recipe.converters;


import org.junit.Before;
import org.junit.Test;
import org.libermundi.recipe.commands.CategoryCommand;
import org.libermundi.recipe.domain.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CategoryToCategoryCommandTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String NAME = "Category Name";
    CategoryToCategoryCommand categoryToCategoryCommand;

    @Before
    public void setUp() throws Exception {
        categoryToCategoryCommand = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(categoryToCategoryCommand.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(categoryToCategoryCommand.convert(new Category()));
    }

    @Test
    public void convert() throws Exception {
        //given
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setName(NAME);

        //when
        CategoryCommand categoryCommand = categoryToCategoryCommand.convert(category);

        //then
        assertEquals(ID_VALUE, categoryCommand.getId());
        assertEquals(NAME, categoryCommand.getName());

    }

}
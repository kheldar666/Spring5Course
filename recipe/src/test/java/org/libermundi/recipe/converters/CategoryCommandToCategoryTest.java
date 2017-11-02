package org.libermundi.recipe.converters;

import org.junit.Before;
import org.junit.Test;
import org.libermundi.recipe.commands.CategoryCommand;
import org.libermundi.recipe.domain.Category;
import org.libermundi.recipe.repositories.CategoryRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CategoryCommandToCategoryTest {
    private static final Long ID = 1L;
    private static final String NAME = "Category Name";


    Converter<CategoryCommand,Category> converter;

    CategoryCommand categoryCommand;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        converter = new CategoryCommandToCategory(categoryRepository);
        categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID);
        categoryCommand.setName(NAME);

        Category category = new Category();
        category.setId(ID);
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
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
package org.libermundi.recipe.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    private final static Long ID_VALUE = 4L;

    private Category category;

    @Before
    public void setup() {
        this.category = new Category();
        this.category.setId(ID_VALUE);
    }

    @Test
    public void getId() throws Exception {
        assertEquals(ID_VALUE,category.getId());
    }

    @Test
    public void getName() throws Exception {
    }

    @Test
    public void getRecipes() throws Exception {
    }

    @Test
    public void setName() throws Exception {
    }


    @Test
    public void testToString() throws Exception {
        category.toString();
    }
}
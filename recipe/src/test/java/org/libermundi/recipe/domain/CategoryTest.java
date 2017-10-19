package org.libermundi.recipe.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    private Category category;

    @Before
    public void setup() {
        this.category = new Category();
    }

    @Test
    public void getId() throws Exception {
        Long idValue = 4L;
        category.setId(idValue);
        assertEquals(idValue,category.getId());

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

}
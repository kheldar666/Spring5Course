package org.libermundi.recipe.bootstrap;

import com.google.common.collect.Iterators;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.libermundi.recipe.domain.Recipe;
import org.libermundi.recipe.repositories.RecipeRepository;
import org.libermundi.recipe.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationLoaderIT {

    @Autowired
    RecipeRepository recipeRepository;

    @Test
    @Transactional
    public void onApplicationEvent() throws Exception {
        assertEquals(2,Iterators.size(recipeRepository.findAll().iterator()));


        Optional<Recipe> guacamole = recipeRepository.findByName("Perfect Guacamole");
        assertNotNull(guacamole.get());
        assertEquals(2,Iterators.size(guacamole.get().getCategories().iterator()));
        assertEquals(8,Iterators.size(guacamole.get().getIngredients().iterator()));
    }

}
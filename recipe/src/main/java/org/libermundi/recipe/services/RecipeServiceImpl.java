package org.libermundi.recipe.services;

import com.google.common.collect.Iterators;
import lombok.extern.slf4j.Slf4j;
import org.libermundi.recipe.domain.Recipe;
import org.libermundi.recipe.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Iterable<Recipe> getAllRecipes() {
        Iterable<Recipe> recipes = recipeRepository.findAll();

        if(log.isDebugEnabled()){
            log.debug("Loading All Recipes.{} Recipe(s) found.", Iterators.size(recipes.iterator()));
        }

        return recipes;
    }


}

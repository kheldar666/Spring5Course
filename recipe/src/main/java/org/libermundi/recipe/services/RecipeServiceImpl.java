package org.libermundi.recipe.services;

import com.google.common.collect.Iterators;
import lombok.extern.slf4j.Slf4j;
import org.libermundi.recipe.commands.RecipeCommand;
import org.libermundi.recipe.converters.RecipeCommandToRecipe;
import org.libermundi.recipe.converters.RecipeToRecipeCommand;
import org.libermundi.recipe.domain.Recipe;
import org.libermundi.recipe.repositories.RecipeRepository;
import org.libermundi.recipe.utils.NullAwareBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    private RecipeRepository recipeRepository;
    private RecipeCommandToRecipe recipeCommandToRecipe;
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Iterable<Recipe> getAllRecipes() {
        Iterable<Recipe> recipes = recipeRepository.findAll();

        if(log.isDebugEnabled()){
            log.debug("Loading All Recipes.{} Recipe(s) found.", Iterators.size(recipes.iterator()));
        }

        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if(! recipeOptional.isPresent()){
            throw new RuntimeException("Recipe not Found !");
        }

        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);
        Recipe mergedRecipe;

        if(detachedRecipe.getId() != null) {
            mergedRecipe = recipeRepository.findById(detachedRecipe.getId()).get();
            NullAwareBeanUtil.copyProperties(detachedRecipe,mergedRecipe);
        } else {
            mergedRecipe=detachedRecipe;
        }

        Recipe savedRecipe = recipeRepository.save(mergedRecipe);

        if(log.isDebugEnabled()){
            log.debug("Save recipe : " + savedRecipe);
        }

        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}

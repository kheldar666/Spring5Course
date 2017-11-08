package org.libermundi.recipe.repositories;

import org.libermundi.recipe.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient,String> {

    void deleteByIdAndRecipeId(String id, String recipeId);
}

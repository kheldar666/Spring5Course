package org.libermundi.recipe.repositories;

import org.libermundi.recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RecipeRepository extends CrudRepository<Recipe,String> {
    Optional<Recipe> findByName(String name);
}

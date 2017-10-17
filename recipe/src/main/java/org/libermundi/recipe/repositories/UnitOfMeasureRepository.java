package org.libermundi.recipe.repositories;

import org.libermundi.recipe.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {
    Optional<UnitOfMeasure> findByName(String name);
    Optional<UnitOfMeasure> findByUnit(String unit);
}

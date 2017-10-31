package org.libermundi.recipe.services;

import org.libermundi.recipe.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {
    Iterable<UnitOfMeasureCommand> findAll();
}

package org.libermundi.recipe.services;

import org.libermundi.recipe.commands.UnitOfMeasureCommand;
import org.libermundi.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import org.libermundi.recipe.domain.UnitOfMeasure;
import org.libermundi.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {
    UnitOfMeasureRepository unitOfMeasureRepository;

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository,
                                    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Iterable<UnitOfMeasureCommand> findAll() {
        Iterable<UnitOfMeasure> allUom = unitOfMeasureRepository.findAll();

        Set<UnitOfMeasureCommand> allUomCommand = new LinkedHashSet<>();

        allUom.forEach(unitOfMeasure -> allUomCommand.add(unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasure)));

        return allUomCommand;
    }
}

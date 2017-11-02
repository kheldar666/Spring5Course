package org.libermundi.recipe.converters;

import lombok.Synchronized;
import org.libermundi.recipe.commands.UnitOfMeasureCommand;
import org.libermundi.recipe.domain.UnitOfMeasure;
import org.libermundi.recipe.repositories.UnitOfMeasureRepository;
import org.libermundi.recipe.utils.NullAwareBeanUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public UnitOfMeasureCommandToUnitOfMeasure(UnitOfMeasureRepository unitOfMeasureRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Nullable
    @Override
    @Synchronized
    public UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {
        if(unitOfMeasureCommand == null){
            return null;
        }
        UnitOfMeasure unitOfMeasure;

        if(unitOfMeasureCommand.getId() != null) {
            unitOfMeasure = unitOfMeasureRepository.findById(unitOfMeasureCommand.getId()).get();
        } else {
            unitOfMeasure = new UnitOfMeasure();
        }

        NullAwareBeanUtil.copyProperties(unitOfMeasureCommand,unitOfMeasure);

        return unitOfMeasure;
    }
}

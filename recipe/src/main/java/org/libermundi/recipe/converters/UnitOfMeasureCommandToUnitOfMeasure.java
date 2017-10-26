package org.libermundi.recipe.converters;

import lombok.Synchronized;
import org.libermundi.recipe.commands.UnitOfMeasureCommand;
import org.libermundi.recipe.domain.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure extends IdentityCommandToIdentity implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Nullable
    @Override
    @Synchronized
    public UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {
        if(unitOfMeasureCommand == null){
            return null;
        }

        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();

        convertIdentityCommand(unitOfMeasureCommand,unitOfMeasure);

        unitOfMeasure.setName(unitOfMeasureCommand.getName());
        unitOfMeasure.setUnit(unitOfMeasureCommand.getUnit());

        return unitOfMeasure;
    }
}

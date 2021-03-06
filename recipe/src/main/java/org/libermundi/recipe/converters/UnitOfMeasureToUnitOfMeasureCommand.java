package org.libermundi.recipe.converters;

import lombok.Synchronized;
import org.libermundi.recipe.commands.UnitOfMeasureCommand;
import org.libermundi.recipe.domain.UnitOfMeasure;
import org.libermundi.recipe.utils.NullAwareBeanUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 6/21/17.
 */
@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {

        if (unitOfMeasure != null) {
            final UnitOfMeasureCommand uomc = new UnitOfMeasureCommand();
            NullAwareBeanUtil.copyProperties(unitOfMeasure,uomc);
            return uomc;
        }
        return null;
    }
}

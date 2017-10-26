package org.libermundi.recipe.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand extends IdentityCommand  {
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand unit;
}
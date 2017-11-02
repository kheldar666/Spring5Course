package org.libermundi.recipe.commands;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id, name, created, updated"})
public class IngredientCommand extends IdentityCommand  {
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand unit;
}

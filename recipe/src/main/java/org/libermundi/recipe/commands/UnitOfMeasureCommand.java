package org.libermundi.recipe.commands;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id, name"})
public class UnitOfMeasureCommand extends IdentityCommand  {
    private String name;
    private String unit;
}

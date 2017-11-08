package org.libermundi.recipe.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"name"}, callSuper = true)
public class UnitOfMeasure extends Identity{
    private String name;

    private String unit;

    public UnitOfMeasure(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }
}

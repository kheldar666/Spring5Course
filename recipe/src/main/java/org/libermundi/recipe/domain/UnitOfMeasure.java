package org.libermundi.recipe.domain;

import lombok.*;

import javax.persistence.Entity;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class UnitOfMeasure extends Identity{
    private String name;

    private String unit;

    public UnitOfMeasure() {
    }

    public UnitOfMeasure(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }

}

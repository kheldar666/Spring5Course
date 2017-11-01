package org.libermundi.recipe.domain;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"name"}, callSuper = true)
@Entity
public class UnitOfMeasure extends Identity{
    private String name;

    private String unit;

    public UnitOfMeasure(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }
}

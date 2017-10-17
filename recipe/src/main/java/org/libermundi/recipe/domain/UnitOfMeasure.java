package org.libermundi.recipe.domain;

import javax.persistence.Entity;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "UnitOfMeasure{" +
                "name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                "} " + super.toString();
    }
}

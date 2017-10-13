package org.libermundi.recipe.domain;

import javax.persistence.Entity;

@Entity
public class UnitOfMeasure extends Identity{
    private String name;

    public UnitOfMeasure(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UnitOfMeasure{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}

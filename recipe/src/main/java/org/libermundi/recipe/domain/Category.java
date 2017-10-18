package org.libermundi.recipe.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@Entity
public class Category extends Identity {
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

}

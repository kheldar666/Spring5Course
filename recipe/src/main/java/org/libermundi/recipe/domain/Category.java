package org.libermundi.recipe.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString(of={"name"},callSuper = true)
public class Category extends Identity {
    private String name;

    private Set<Recipe> recipes;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

}

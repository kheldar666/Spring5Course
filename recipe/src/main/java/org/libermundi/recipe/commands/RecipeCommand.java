package org.libermundi.recipe.commands;

import lombok.*;
import org.libermundi.recipe.domain.Difficulty;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"categories","ingredients","notes","image","directions"})
@EqualsAndHashCode(exclude = {"categories","ingredients","notes"})
public class RecipeCommand extends IdentityCommand  {
    private String name;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<IngredientCommand> ingredients = new LinkedHashSet<>();
    private Difficulty difficulty;
    private NotesCommand notes;
    private Set<CategoryCommand> categories = new LinkedHashSet<>();
}

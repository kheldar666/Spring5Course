package org.libermundi.recipe.commands;

import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.libermundi.recipe.domain.Difficulty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id, name"})
public class RecipeCommand extends IdentityCommand  {
    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @Min(1)
    @Max(999)
    private Integer prepTime;

    @Min(1)
    @Max(999)
    private Integer cookTime;

    @Min(1)
    @Max(100)
    private Integer servings;

    private String source;

    @URL
    private String url;

    @NotBlank
    private String directions;
    private Byte[] image;
    private Set<IngredientCommand> ingredients = new LinkedHashSet<>();
    private Difficulty difficulty;
    private NotesCommand notes;
    private Set<CategoryCommand> categories = new LinkedHashSet<>();
}

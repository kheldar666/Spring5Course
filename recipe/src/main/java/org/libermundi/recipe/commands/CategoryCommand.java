package org.libermundi.recipe.commands;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id, name"})
public class CategoryCommand extends IdentityCommand {
    private String name;
}

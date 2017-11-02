package org.libermundi.recipe.commands;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id, name, created, updated"})
public class NotesCommand extends IdentityCommand  {
    private String notes;
}

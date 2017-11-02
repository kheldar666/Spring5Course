package org.libermundi.recipe.commands;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id"})
public class NotesCommand extends IdentityCommand  {
    private String notes;
}

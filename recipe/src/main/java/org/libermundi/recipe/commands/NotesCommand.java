package org.libermundi.recipe.commands;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotesCommand extends IdentityCommand  {
    private String notes;
}

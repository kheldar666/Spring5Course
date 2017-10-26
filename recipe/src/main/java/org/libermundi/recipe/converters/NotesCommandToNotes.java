package org.libermundi.recipe.converters;

import lombok.Synchronized;
import org.libermundi.recipe.commands.NotesCommand;
import org.libermundi.recipe.domain.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes extends IdentityCommandToIdentity implements Converter<NotesCommand, Notes> {
    @Nullable
    @Override
    @Synchronized
    public Notes convert(NotesCommand notesCommand) {
        if(notesCommand == null) {
            return null;
        }

        Notes notes = new Notes();

        convertIdentityCommand(notesCommand,notes);

        notes.setNotes(notesCommand.getNotes());

        return notes;

    }
}

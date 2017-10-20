package org.libermundi.recipe.converters;

import lombok.Synchronized;
import org.libermundi.recipe.commands.NotesCommand;
import org.libermundi.recipe.domain.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {
    @Nullable
    @Override
    @Synchronized
    public Notes convert(NotesCommand notesCommand) {
        if(notesCommand == null) {
            return null;
        }

        Notes notes = new Notes();
        notes.setId(notesCommand.getId());
        notes.setNotes(notesCommand.getNotes());

        return notes;

    }
}

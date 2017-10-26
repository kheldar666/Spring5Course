package org.libermundi.recipe.converters;



import lombok.Synchronized;
import org.libermundi.recipe.commands.NotesCommand;
import org.libermundi.recipe.domain.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 6/21/17.
 */
@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes source) {
        if (source == null) {
            return null;
        }

        final NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(source.getId());
        notesCommand.setNotes(source.getNotes());
        return notesCommand;
    }
}

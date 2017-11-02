package org.libermundi.recipe.converters;

import lombok.Synchronized;
import org.libermundi.recipe.commands.NotesCommand;
import org.libermundi.recipe.domain.Notes;
import org.libermundi.recipe.repositories.NotesRepository;
import org.libermundi.recipe.utils.NullAwareBeanUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {
    private NotesRepository notesRepository;

    public NotesCommandToNotes(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    @Nullable
    @Override
    @Synchronized
    public Notes convert(NotesCommand notesCommand) {
        if(notesCommand == null) {
            return null;
        }

        Notes notes;

        if(notesCommand.getId() != null) {
            notes = notesRepository.findById(notesCommand.getId()).get();
        } else {
            notes = new Notes();
        }

        NullAwareBeanUtil.copyProperties(notesCommand,notes);

        return notes;

    }
}

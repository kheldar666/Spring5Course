package org.libermundi.recipe.converters;

import org.junit.Before;
import org.junit.Test;
import org.libermundi.recipe.commands.NotesCommand;
import org.libermundi.recipe.domain.Notes;
import org.springframework.core.convert.converter.Converter;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    private static final Long ID = 1L;
    private static final String NOTES = "Somes notes";

    Converter<NotesCommand, Notes> converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();
    }

    @Test
    public void convertNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void convertEmpty() throws Exception {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert() throws Exception {
        // Given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(ID);
        notesCommand.setNotes(NOTES);

        // When
        Notes notes = converter.convert(notesCommand);

        // Then
        assertNotNull(notes);
        assertEquals(ID, notes.getId());
        assertEquals(NOTES, notes.getNotes());

    }

}
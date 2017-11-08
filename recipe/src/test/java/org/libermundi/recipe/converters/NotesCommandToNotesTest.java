package org.libermundi.recipe.converters;

import org.junit.Before;
import org.junit.Test;
import org.libermundi.recipe.commands.NotesCommand;
import org.libermundi.recipe.domain.Notes;
import org.libermundi.recipe.repositories.NotesRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class NotesCommandToNotesTest {

    private static final String ID = "1234567879";
    private static final String NOTES = "Somes notes";

    Converter<NotesCommand, Notes> converter;

    @Mock
    NotesRepository notesRepository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        Notes notes = new Notes();
        notes.setId(ID);
        when(notesRepository.findById(anyString())).thenReturn(Optional.of(notes));

        converter = new NotesCommandToNotes(notesRepository);
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
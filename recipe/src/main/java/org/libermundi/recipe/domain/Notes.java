package org.libermundi.recipe.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class Notes extends Identity {

    @Lob
    private String notes;

    public Notes() {
    }

    public Notes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

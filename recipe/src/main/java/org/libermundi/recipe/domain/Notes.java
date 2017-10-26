package org.libermundi.recipe.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Notes extends Identity {

    @Lob
    private String notes;

    public Notes() {
    }

    public Notes(String notes) {
        this.notes = notes;
    }

}

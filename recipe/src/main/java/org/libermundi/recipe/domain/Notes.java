package org.libermundi.recipe.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Getter
@Setter
@NoArgsConstructor
public class Notes extends Identity {

    private String notes;

    public Notes(String notes) {
        this.notes = notes;
    }

}

package org.libermundi.recipe.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Notes extends Identity {

    @Lob
    private String notes;

    public Notes(String notes) {
        this.notes = notes;
    }

}

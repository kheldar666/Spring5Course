package org.libermundi.recipe.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Notes extends Identity {

    private String notes;

    public Notes(String notes) {
        this.notes = notes;
    }

}

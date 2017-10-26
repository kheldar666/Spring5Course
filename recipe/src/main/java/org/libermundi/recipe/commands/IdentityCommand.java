package org.libermundi.recipe.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public abstract class IdentityCommand {
    private Long id;

    private Date created;

    private Date updated;
}

package org.libermundi.recipe.commands;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Transient;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public abstract class IdentityCommand {
    private Long id;

    @Transient
    private final String uuid = UUID.randomUUID().toString();

    private Date created;

    private Date updated;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        IdentityCommand identity = (IdentityCommand) o;

        return id != null ? id.equals(identity.id) : uuid.equals(identity.uuid);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : uuid.hashCode());
        return result;
    }
}

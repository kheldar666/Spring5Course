package org.libermundi.recipe.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.libermundi.recipe.listeners.IdentityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString(of = "id")
@MappedSuperclass
@EntityListeners(IdentityListener.class)
public abstract class Identity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private final String uuid = UUID.randomUUID().toString();

    private Date created;

    private Date updated;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Identity identity = (Identity) o;

        return id != null ? id.equals(identity.id) : uuid.equals(identity.uuid);
    }

    @Override
    public int hashCode() {
        return (id != null ? id.hashCode() : uuid.hashCode());
    }
}
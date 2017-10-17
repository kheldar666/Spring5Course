package org.libermundi.recipe.domain;

import org.libermundi.recipe.listeners.IdentityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(IdentityListener.class)
public abstract class Identity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date created;

    private Date updated;

    @Transient
    private UUID uuid = UUID.randomUUID();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Identity identity = (Identity) o;

        return id != null ? id.equals(identity.id) : uuid.equals(identity.uuid);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : uuid.hashCode();
    }

    @Override
    public String toString() {
        return "Identity{" +
                "id=" + id +
                '}';
    }
}

package org.libermundi.recipe.domain;

import lombok.*;
import org.libermundi.recipe.listeners.IdentityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@EqualsAndHashCode(exclude = {"uuid"})
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
}

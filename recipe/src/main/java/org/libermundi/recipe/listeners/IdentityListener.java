package org.libermundi.recipe.listeners;

import org.libermundi.recipe.domain.Identity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

public class IdentityListener {
    @PrePersist
    public void identityPrePersist(Identity identity) {
        identity.setCreated(new Date());
        identity.setUpdated(new Date());
    }

    @PreUpdate
    public void identityPreUpdate(Identity identity) {
        identity.setUpdated(new Date());
    }
}

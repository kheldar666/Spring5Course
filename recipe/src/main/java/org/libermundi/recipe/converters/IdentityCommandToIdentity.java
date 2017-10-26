package org.libermundi.recipe.converters;

import org.libermundi.recipe.commands.IdentityCommand;
import org.libermundi.recipe.domain.Identity;
import org.springframework.lang.Nullable;

public abstract class IdentityCommandToIdentity {
    @Nullable
    public Identity convertIdentityCommand(IdentityCommand identityCommand, Identity identity) {
        if(identityCommand != null) {
            identity.setId(identityCommand.getId());
            identity.setCreated(identityCommand.getCreated());
            identity.setUpdated(identityCommand.getUpdated());
        }

        return identity;
    }
}

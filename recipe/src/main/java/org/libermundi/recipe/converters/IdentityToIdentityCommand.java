package org.libermundi.recipe.converters;

import org.libermundi.recipe.commands.IdentityCommand;
import org.libermundi.recipe.domain.Identity;
import org.springframework.lang.Nullable;

public abstract class IdentityToIdentityCommand {
    @Nullable
    protected IdentityCommand convertIdentity(Identity identity, IdentityCommand identityCommand) {

        if(identity != null) {
            identityCommand.setId(identity.getId());
            identityCommand.setCreated(identity.getCreated());
            identityCommand.setUpdated(identity.getUpdated());
        }

        return identityCommand;
    }
}

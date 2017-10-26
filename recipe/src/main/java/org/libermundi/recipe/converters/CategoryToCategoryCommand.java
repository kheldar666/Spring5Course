package org.libermundi.recipe.converters;

import lombok.Synchronized;
import org.libermundi.recipe.commands.CategoryCommand;
import org.libermundi.recipe.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand extends IdentityToIdentityCommand implements Converter<Category,CategoryCommand> {
    @Nullable
    @Override
    @Synchronized
    public CategoryCommand convert(Category category) {
        if(category == null){
            return null;
        }
        final CategoryCommand categoryCommand = new CategoryCommand();

            convertIdentity(category,categoryCommand);

            categoryCommand.setName(category.getName());

        return categoryCommand;
    }
}

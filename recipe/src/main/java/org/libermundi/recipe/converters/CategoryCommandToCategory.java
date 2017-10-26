package org.libermundi.recipe.converters;

import lombok.Synchronized;
import org.libermundi.recipe.commands.CategoryCommand;
import org.libermundi.recipe.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory extends IdentityCommandToIdentity implements Converter<CategoryCommand, Category> {
    @Nullable
    @Override
    @Synchronized
    public Category convert(CategoryCommand categoryCommand) {
        if(categoryCommand == null) {
            return null;
        }

        final Category category = new Category();

        convertIdentityCommand(categoryCommand,category);

        category.setName(categoryCommand.getName());

        return category;
    }
}

package org.libermundi.recipe.converters;

import lombok.Synchronized;
import org.libermundi.recipe.commands.CategoryCommand;
import org.libermundi.recipe.domain.Category;
import org.libermundi.recipe.repositories.CategoryRepository;
import org.libermundi.recipe.utils.NullAwareBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryCommandToCategory(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Nullable
    @Override
    @Synchronized
    public Category convert(CategoryCommand categoryCommand) {
        if(categoryCommand == null) {
            return null;
        }

        Category category;

        if(categoryCommand.getId() != null) {
            category = categoryRepository.findById(categoryCommand.getId()).get();
        } else {
            category = new Category();
        }

        NullAwareBeanUtil.copyProperties(categoryCommand,category);

        return category;
    }
}

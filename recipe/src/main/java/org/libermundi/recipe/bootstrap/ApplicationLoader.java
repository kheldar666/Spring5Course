package org.libermundi.recipe.bootstrap;

import org.libermundi.recipe.domain.Category;
import org.libermundi.recipe.domain.UnitOfMeasure;
import org.libermundi.recipe.repositories.CategoryRepository;
import org.libermundi.recipe.repositories.RecipeRepository;
import org.libermundi.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationLoader implements ApplicationListener<ContextRefreshedEvent> {
    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    public ApplicationLoader(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
                             UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(categoryRepository.count() == 0){
            initData();
        }

    }

    private void initData() {
        // Let's create some Categories
        List<Category> categories = new ArrayList<>();
            categories.add(new Category("American"));
            categories.add(new Category("Italian"));
            categories.add(new Category("French"));
            categories.add(new Category("Fast Food"));
            categories.add(new Category("Mexican"));

        categoryRepository.saveAll(categories);

        // Let's create some UoM
        List<UnitOfMeasure> measures = new ArrayList<>();
            measures.add(new UnitOfMeasure("Milliliter"));
            measures.add(new UnitOfMeasure("Deciliter"));
            measures.add(new UnitOfMeasure("Gram(s)"));
            measures.add(new UnitOfMeasure("Piece(s)"));
            measures.add(new UnitOfMeasure("Tea Spoon(s)"));
            measures.add(new UnitOfMeasure("Cup(s)"));

        unitOfMeasureRepository.saveAll(measures);

    }
}

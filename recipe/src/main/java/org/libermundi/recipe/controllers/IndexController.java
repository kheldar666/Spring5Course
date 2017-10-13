package org.libermundi.recipe.controllers;

import org.libermundi.recipe.domain.Category;
import org.libermundi.recipe.domain.UnitOfMeasure;
import org.libermundi.recipe.repositories.CategoryRepository;
import org.libermundi.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage(){
        Optional<Category> category = categoryRepository.findByName("French");
        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByName("Milliliter");
        System.out.println(category.get());
        System.out.println(unitOfMeasure.get());
        return "index";
    }
}

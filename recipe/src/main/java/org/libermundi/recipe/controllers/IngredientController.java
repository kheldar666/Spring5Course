package org.libermundi.recipe.controllers;

import org.libermundi.recipe.commands.IngredientCommand;
import org.libermundi.recipe.commands.RecipeCommand;
import org.libermundi.recipe.services.IngredientService;
import org.libermundi.recipe.services.RecipeService;
import org.libermundi.recipe.services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IngredientController {
    private RecipeService recipeService;

    private IngredientService ingredientService;

    private UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService,
                                UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients/list")
    public String list(Model model, @PathVariable Long recipeId) {
        RecipeCommand recipe = recipeService.findById(recipeId);

        model.addAttribute("recipe",recipe);

        return "/recipe/ingredients/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients/{id}/edit")
    public String edit(Model model, @PathVariable Long recipeId, @PathVariable Long id) {
        IngredientCommand ingredientCommand = ingredientService.findIngredient(recipeId,id);
        RecipeCommand recipeCommand = recipeService.findById(recipeId);

        model.addAttribute("ingredient",ingredientCommand);
        model.addAttribute("recipe",recipeCommand);
        model.addAttribute("uomList",unitOfMeasureService.findAll());

        return "/recipe/ingredients/edit";
    }

    @PostMapping
    @RequestMapping("/recipe/{recipeId}/ingredients/{ingredientId}/save")
    public String save(@ModelAttribute IngredientCommand ingredient, @PathVariable Long recipeId, @PathVariable Long ingredientId) {

        ingredientService.saveIngredient(recipeId,ingredient);

        return "redirect:/recipe/" + recipeId + "/ingredients/list";
    }
}

package org.libermundi.recipe.controllers;


import org.libermundi.recipe.commands.RecipeCommand;
import org.libermundi.recipe.converters.RecipeToRecipeCommand;
import org.libermundi.recipe.domain.Recipe;
import org.libermundi.recipe.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {
    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/show")
    public String show(Model model, @PathVariable Long id) {
        model.addAttribute("recipe",recipeService.findById(id));

        return "/recipe/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/edit")
    public String edit(Model model, @PathVariable Long id) {
        RecipeCommand command = recipeService.findById(id);
        model.addAttribute("recipe",command);
        model.addAttribute("cancelUrl",getCancelUrl(command));

        return "/recipe/form";
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/delete")
    public String delete(@PathVariable Long id) {
        recipeService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping
    @RequestMapping("/recipe/new")
    public String create(Model model) {
        RecipeCommand command = new RecipeCommand();
        model.addAttribute("recipe",command);
        model.addAttribute("cancelUrl",getCancelUrl(command));

        return "/recipe/form";
    }

    @PostMapping
    @RequestMapping("/recipe/save")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedRecipeCommand = recipeService.saveRecipe(command);

        return "redirect:/recipe/" + savedRecipeCommand.getId() + "/show";
    }


    private String getCancelUrl(RecipeCommand command){
            if(command.getId() == null) {
                return "/";
            }
            return "/recipe/" + command.getId() + "/show";
    }
}

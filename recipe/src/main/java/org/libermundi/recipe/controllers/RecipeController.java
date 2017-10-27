package org.libermundi.recipe.controllers;


import org.libermundi.recipe.commands.RecipeCommand;
import org.libermundi.recipe.converters.RecipeToRecipeCommand;
import org.libermundi.recipe.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {
    private final RecipeService recipeService;

    private final RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    public RecipeController(RecipeService recipeService, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeService = recipeService;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @RequestMapping("/recipe/show/{id}")
    public String show(Model model, @PathVariable Long id) {
        model.addAttribute("recipe",getById(id));

        return "/recipe/show";
    }

    @RequestMapping("/recipe/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        RecipeCommand command = getById(id);
        model.addAttribute("recipe",command);
        model.addAttribute("cancelUrl",getCancelUrl(command));

        return "/recipe/form";
    }

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
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/show/" + savedRecipeCommand.getId();
    }


    private String getCancelUrl(RecipeCommand command){
            if(command.getId() == null) {
                return "/";
            }
            return "/recipe/show/" + command.getId();
    }



    private RecipeCommand getById(Long id) {
        return recipeToRecipeCommand.convert(recipeService.findById(id));
    }
}

package org.libermundi.recipe.controllers;


import lombok.extern.slf4j.Slf4j;
import org.libermundi.recipe.commands.RecipeCommand;
import org.libermundi.recipe.converters.RecipeToRecipeCommand;
import org.libermundi.recipe.domain.Recipe;
import org.libermundi.recipe.exceptions.NotFoundException;
import org.libermundi.recipe.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class RecipeController {
    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{id}/show")
    public String show(Model model, @PathVariable Long id) {
        model.addAttribute("recipe",recipeService.findById(id));

        return "recipe/show";
    }

    @GetMapping("/recipe/{id}/edit")
    public String edit(Model model, @PathVariable Long id) {
        RecipeCommand command = recipeService.findById(id);
        model.addAttribute("recipe",command);
        model.addAttribute("cancelUrl",getCancelUrl(command));

        return "recipe/form";
    }

    @GetMapping("/recipe/{id}/delete")
    public String delete(@PathVariable Long id) {
        recipeService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/recipe/new")
    public String create(Model model) {
        RecipeCommand command = new RecipeCommand();
        model.addAttribute("recipe",command);
        model.addAttribute("cancelUrl",getCancelUrl(command));

        return "recipe/form";
    }

    @PostMapping("/recipe/save")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedRecipeCommand = recipeService.saveRecipe(command);

        return "redirect:/recipe/" + savedRecipeCommand.getId() + "/show";
    }

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception e) {
        log.error("Handling 'NotFoundException'");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("errors/404");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        modelAndView.addObject("exception",e);
        return modelAndView;
    }


    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException(Exception e) {
        log.error("Handling 'NumberFormatException'");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("errors/400");
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        modelAndView.addObject("exception",e);
        return modelAndView;
    }
    private String getCancelUrl(RecipeCommand command){
            if(command.getId() == null) {
                return "/";
            }
            return "/recipe/" + command.getId() + "/show";
    }
}

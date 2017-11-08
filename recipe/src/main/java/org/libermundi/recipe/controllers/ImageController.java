package org.libermundi.recipe.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.libermundi.recipe.commands.RecipeCommand;
import org.libermundi.recipe.services.ImageService;
import org.libermundi.recipe.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {
    private final RecipeService recipeService;

    private final ImageService imageService;

    @Autowired
    public ImageController(RecipeService recipeService, ImageService imageService) {
        this.recipeService = recipeService;
        this.imageService = imageService;
    }

    @GetMapping("/recipe/{id}/editimage")
    public String image(Model model, @PathVariable String id) {
        RecipeCommand command = recipeService.findById(id);
        model.addAttribute("recipe",command);
        model.addAttribute("cancelUrl", getRecipeUrl(command));

        return "recipe/imgform";
    }

    @PostMapping("/recipe/{id}/editimage")
    public String saveImage(@PathVariable String id, @RequestParam("imagefile") MultipartFile file) {

        imageService.saveImage(id,file);

        return "redirect:/recipe/" + id + "/show";
    }

    @GetMapping("/recipe/{id}/image")
    public void renderImageFromDb(@PathVariable String id, HttpServletResponse response) throws IOException {
        RecipeCommand recipeCommand = recipeService.findById(id);

        if (recipeCommand.getImage() != null) {
            byte[] byteArray = new byte[recipeCommand.getImage().length];
            int i = 0;

            for (Byte wrappedByte : recipeCommand.getImage()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }

    private String getRecipeUrl(RecipeCommand command){
        if(command.getId() == null) {
            return "/";
        }
        return "/recipe/" + command.getId() + "/show";
    }
}

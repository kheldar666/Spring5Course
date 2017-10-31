package org.libermundi.recipe.bootstrap;

import org.libermundi.recipe.domain.*;
import org.libermundi.recipe.repositories.CategoryRepository;
import org.libermundi.recipe.repositories.RecipeRepository;
import org.libermundi.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    @Transactional
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
            measures.add(new UnitOfMeasure("Milliliter","ml"));
            measures.add(new UnitOfMeasure("Deciliter","dl"));
            measures.add(new UnitOfMeasure("Gram(s)","g"));
            measures.add(new UnitOfMeasure("Piece(s)","p"));
            measures.add(new UnitOfMeasure("Teaspoon(s)","Tsp"));
            measures.add(new UnitOfMeasure("Tablespoon(s)","Tbsp"));
            measures.add(new UnitOfMeasure("Cup(s)","cup"));
            measures.add(new UnitOfMeasure("Dash","dash"));
            measures.add(new UnitOfMeasure("Pint","pint"));
            measures.add(new UnitOfMeasure("---","none"));

        unitOfMeasureRepository.saveAll(measures);


        Recipe guacamole = new Recipe("Perfect Guacamole");
            guacamole.setPrepTime(Integer.valueOf(10));
            guacamole.setCookTime(Integer.valueOf(0));
            guacamole.setServings(Integer.valueOf(4));
            guacamole.setDifficulty(Difficulty.EASY);
            guacamole.getCategories().add(categoryRepository.findByName("Mexican").get());
            guacamole.getCategories().add(categoryRepository.findByName("American").get());
            guacamole.setUrl("http://www.simplyrecipes.com/recipes/perfect_guacamole/");
            guacamole.setSource("Simple Recipes");

            guacamole.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of " +
                    "the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an " +
                    "Avocado.) Place in a bowl." +
                    "\n\n" +
                    "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don\'t overdo it! The guacamole should" +
                    " be a little chunky.)" +
                    "\n\n" +
                    "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or " +
                    "lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado" +
                    " and will help delay the avocados from turning brown." +
                    "\n\n" +
                    "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their" +
                    " hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree" +
                    " of hotness." +
                    "\n\n" +
                    "Remember that much of this is done to taste because of the variability in the fresh" +
                    " ingredients. Start with this recipe and adjust to your taste." +
                    "\n\n" +
                    "4 Cover with plastic and chill to" +
                    " store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it." +
                    " (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until " +
                    "ready to serve." +
                    "\n\n" +
                    "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your" +
                    " guacamole, add it just before serving.");

            guacamole.setNotes(new Notes("Variations" +
                    "\n\n" +
                    "For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados." +
                    "\n\n" +
                    "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches" +
                    " in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries." +
                    "\n\n" +
                    "The simplest version of guacamole is just mashed avocados with salt. Don\'t let the lack of " +
                    "availability of other ingredients stop you from making guacamole." +
                    "\n\n" +
                    "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole " +
                    "dip. Purists may be horrified, but so what? It tastes great." +
                    "\n\n" +
                    "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4vkR8pwoo"));



            guacamole.addIngredient(new Ingredient("ripe avocado", new BigDecimal("2"),unitOfMeasureRepository.findByUnit("none").get()));
            guacamole.addIngredient(new Ingredient("Kosher salt", new BigDecimal("0.5"),unitOfMeasureRepository.findByUnit("Tsp").get()));
            guacamole.addIngredient(new Ingredient("fresh lime juice", new BigDecimal("1"),unitOfMeasureRepository.findByUnit("Tbsp").get()));
            guacamole.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal("2"),unitOfMeasureRepository.findByUnit("Tbsp").get()));
            guacamole.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal("2"),unitOfMeasureRepository.findByUnit("none").get()));
            guacamole.addIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal("2"),unitOfMeasureRepository.findByUnit("Tbsp").get()));
            guacamole.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal("1"),unitOfMeasureRepository.findByUnit("dash").get()));
            guacamole.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal("0.5"),unitOfMeasureRepository.findByUnit("none").get()));

        recipeRepository.save(guacamole);

        Recipe chicken = new Recipe("Spicy Grilled Chicken Tacos");
        chicken.setPrepTime(Integer.valueOf(20));
        chicken.setCookTime(Integer.valueOf(15));
        chicken.setDifficulty(Difficulty.MODERATE);
        chicken.setServings(Integer.valueOf(6));
        chicken.setSource("Simply Recipes");
        chicken.setUrl("http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

        chicken.getCategories().add(categoryRepository.findByName("Mexican").get());

        chicken.setNotes(new Notes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on " +
                "buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, " +
                "and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)"));

        chicken.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, " +
                "cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose " +
                "paste. Add the chicken to the bowl and toss to coat all over." +
                "\n\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings." +
                "\n\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted " +
                "into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes." +
                "\n\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. " +
                "As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat " +
                "for a few seconds on the other side." +
                "\n\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving." +
                "\n\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of " +
                "arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. " +
                "Drizzle with the thinned sour cream. Serve with lime wedges." +
                "\n\n");

        chicken.addIngredient(new Ingredient("ancho chili powder", new BigDecimal("2"), unitOfMeasureRepository.findByUnit("Tbsp").get()));
        chicken.addIngredient(new Ingredient("dried oregano", new BigDecimal("1"), unitOfMeasureRepository.findByUnit("Tsp").get()));
        chicken.addIngredient(new Ingredient("dried cumin", new BigDecimal("1"), unitOfMeasureRepository.findByUnit("Tsp").get()));
        chicken.addIngredient(new Ingredient("sugar", new BigDecimal("1"), unitOfMeasureRepository.findByUnit("Tsp").get()));
        chicken.addIngredient(new Ingredient("salt", new BigDecimal("1"), unitOfMeasureRepository.findByUnit("Tsp").get()));
        chicken.addIngredient(new Ingredient("clove garlic, finely chopped", new BigDecimal("1"), unitOfMeasureRepository.findByUnit("none").get()));
        chicken.addIngredient(new Ingredient("finely grated orange zest", new BigDecimal("1"), unitOfMeasureRepository.findByUnit("Tbsp").get()));
        chicken.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal("3"), unitOfMeasureRepository.findByUnit("Tbsp").get()));
        chicken.addIngredient(new Ingredient("olive oil", new BigDecimal("2"), unitOfMeasureRepository.findByUnit("Tbsp").get()));
        chicken.addIngredient(new Ingredient("skinless, boneless chicken thighs", new BigDecimal("5"), unitOfMeasureRepository.findByUnit("none").get()));
        chicken.addIngredient(new Ingredient("small corn tortillas", new BigDecimal("8"), unitOfMeasureRepository.findByUnit("none").get()));
        chicken.addIngredient(new Ingredient("packed baby arugula", new BigDecimal("3"), unitOfMeasureRepository.findByUnit("cup").get()));
        chicken.addIngredient(new Ingredient("medium ripe avocados, sliced", new BigDecimal("2"), unitOfMeasureRepository.findByUnit("none").get()));
        chicken.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal("4"), unitOfMeasureRepository.findByUnit("none").get()));
        chicken.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal("0.5"), unitOfMeasureRepository.findByUnit("pint").get()));
        chicken.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal("0.25"), unitOfMeasureRepository.findByUnit("none").get()));
        chicken.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal("20"), unitOfMeasureRepository.findByUnit("g").get()));
        chicken.addIngredient(new Ingredient("sour cream thinned with 1/4 cup milk", new BigDecimal("0.5"), unitOfMeasureRepository.findByUnit("cup").get()));
        chicken.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal("1"), unitOfMeasureRepository.findByUnit("none").get()));


        recipeRepository.save(chicken);
    }
}

package org.libermundi.chuck.controllers;

import org.libermundi.chuck.services.JokeSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JokesController {
    private JokeSource jokeSource;

    @Autowired
    public JokesController(JokeSource jokeSource) {
        this.jokeSource = jokeSource;
    }

    @RequestMapping("/")
    public String getJoke(Model model) {
        model.addAttribute("joke",jokeSource.getRandomQuote());
        return "chucknorris";
    }
}

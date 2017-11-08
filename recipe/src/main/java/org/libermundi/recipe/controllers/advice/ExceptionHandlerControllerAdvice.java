package org.libermundi.recipe.controllers.advice;

import lombok.extern.slf4j.Slf4j;
import org.libermundi.recipe.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
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

}
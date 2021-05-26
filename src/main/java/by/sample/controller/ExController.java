package by.sample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExController {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(Model model) {
        model.addAttribute("errorMessage", "Страница не найдена, проверьте правильность введеного адреса");
        return "error";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String runtime(Exception e, Model model) {
        String errorMessage = e.getMessage();
        if(errorMessage == null) errorMessage = "Произошла ошибка, повторите попытку через некоторое время";
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }
}

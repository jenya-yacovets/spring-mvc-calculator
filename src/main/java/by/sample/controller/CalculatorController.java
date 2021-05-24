package by.sample.controller;

import by.sample.config.OperationList;
import by.sample.entity.Operation;
import by.sample.entity.User;
import by.sample.exception.BadRequestException;
import by.sample.model.OperationModel;
import by.sample.service.CalculatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class CalculatorController {
    private CalculatorService calculatorService;
    private final OperationList operationList;

    public CalculatorController(CalculatorService calculatorService, OperationList operationList) {
        this.calculatorService = calculatorService;
        this.operationList = operationList;
    }

    @GetMapping
    public String viewCalculator(@RequestParam(required = false) String message, HttpSession session, Model model){
        // Авторизация
        User user = (User) session.getAttribute("user");
        if(user == null) {
            model.addAttribute("message", "Authorization required");
            return "redirect:/login";
        }

        model.addAttribute("message", message);
        model.addAttribute("operations", operationList.get());
        return "calculation";
    }

    @PostMapping("/execute")
    public String handlerCalculator(@ModelAttribute OperationModel operation, HttpSession session, Model model){
        // Авторизация
        User user = (User) session.getAttribute("user");
        if(user == null) {
            model.addAttribute("message", "Authorization required");
            return "redirect:/login";
        }

        try {
            operation.validation();
            Operation resOperation = calculatorService.execute(operation, user);
            model.addAttribute("message", String.format("Result: %f", resOperation.result()));
        } catch (BadRequestException e) {
            model.addAttribute("message", "Invalid input data");
        }
        return "redirect:/";
    }

    @GetMapping("/history")
    public String viewHistory(HttpSession session, Model model) {
        // Авторизация
        User user = (User) session.getAttribute("user");
        if(user == null) {
            model.addAttribute("message", "Authorization required");
            return "redirect:/login";
        }

        List<Operation> history = calculatorService.getHistory(user);
        model.addAttribute("history", history);
        return "history";
    }
}

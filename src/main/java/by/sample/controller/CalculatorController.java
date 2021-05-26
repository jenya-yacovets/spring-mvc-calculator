package by.sample.controller;

import by.sample.config.OperationList;
import by.sample.entity.Operation;
import by.sample.entity.User;
import by.sample.exception.BadRequestException;
import by.sample.model.OperationModel;
import by.sample.service.CalculatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class CalculatorController {
    private final CalculatorService calculatorService;
    private final OperationList operationList;

    public CalculatorController(CalculatorService calculatorService, OperationList operationList) {
        this.calculatorService = calculatorService;
        this.operationList = operationList;
    }

    @GetMapping
    public String viewCalculator(@RequestParam(required = false) String message, Model model){
        model.addAttribute("message", message);
        model.addAttribute("operationModel", new OperationModel());
        model.addAttribute("operationType", operationList.get());
        return "calculation";
    }

    @PostMapping("/")
    public String handlerCalculator(@ModelAttribute("operationModel") @Valid OperationModel operation, BindingResult bindingResult, HttpSession session, Model model){
        if (!bindingResult.hasErrors()) {
            Operation resOperation = calculatorService.execute(operation, (User) session.getAttribute("user"));
            model.addAttribute("message", String.format("Result: %f", resOperation.result()));
        }
        model.addAttribute("operationType", operationList.get());
        return "calculation";

    }

    @GetMapping("/history")
    public String viewHistory(HttpSession session, Model model) {
        List<Operation> history = calculatorService.getHistory((User) session.getAttribute("user"));
        model.addAttribute("history", history);
        return "history";
    }
}

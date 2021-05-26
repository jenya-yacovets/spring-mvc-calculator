package by.sample.controller;

import by.sample.entity.User;
import by.sample.exception.BadRequestException;
import by.sample.exception.InvalidLoginOrPasswordException;
import by.sample.exception.LoginIsBusyException;
import by.sample.model.UserLoginModel;
import by.sample.model.UserRegisterModel;
import by.sample.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String viewLogin(@RequestParam(required = false) String message, Model model) {
        model.addAttribute("message", message);
        model.addAttribute("user", new UserLoginModel());
        return "login";
    }

    @PostMapping("/login")
    public String handlerLogin(@ModelAttribute("user") @Valid UserLoginModel user, BindingResult bindingResult, HttpSession session, Model model) {
        if (!bindingResult.hasErrors()) {
            try {
                User loginUser = userService.login(user);
                session.setAttribute("user", loginUser);
                model.addAttribute("message", "Ok login");
                return "redirect:/";
            } catch (InvalidLoginOrPasswordException e) {
                model.addAttribute("message", "Логин или пароль введен не верно");
            }
        }
        return "login";
    }

    @GetMapping("/register")
    public String viewRegister(Model model) {
        model.addAttribute("user", new UserRegisterModel());
        return "register";
    }

    @PostMapping("/register")
    public String handlerRegister(@ModelAttribute("user") @Valid UserRegisterModel user, BindingResult bindingResult, Model model) {

        if (!bindingResult.hasErrors()) {
            try {
                userService.register(user);
                model.addAttribute("message", "Ok register");
                return "redirect:/login";
            } catch (LoginIsBusyException e) {
                model.addAttribute("message", "Логин занят, попробуйте использовать другой");
            }
        }
        return "register";
    }

    @GetMapping("/logout")
    public String handlerLogout(HttpSession session, Model model) {
        session.invalidate();
        model.addAttribute("message", "Ok logout");
        return "redirect:/login";
    }
}

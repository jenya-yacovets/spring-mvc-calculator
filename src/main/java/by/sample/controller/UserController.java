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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String viewLogin(@RequestParam(required = false) String message, Model model) {
        model.addAttribute("message", message);
        return "login";
    }

    @PostMapping("/login")
    public String handlerLogin(UserLoginModel user, HttpSession session, Model model) {
        try {
            user.validation();
            User loginUser = userService.login(user);
            session.setAttribute("user", loginUser);
            model.addAttribute("message", "Ok login");
            return "redirect:/";
        } catch (BadRequestException e) {
            model.addAttribute("message", "Данные введены не корректно");
        } catch (InvalidLoginOrPasswordException e) {
            model.addAttribute("message", "Логин или пароль введен не верно");
        }
        return "login";
    }

    @GetMapping("/register")
    public String viewRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String handlerRegister(@ModelAttribute UserRegisterModel user, Model model) {
        try {
            user.validation();
            userService.register(user);
            model.addAttribute("message", "Ok register");
            return "redirect:/login";
        } catch (BadRequestException e) {
            model.addAttribute("message", "Данные введены не корректно");
        } catch (LoginIsBusyException e) {
            model.addAttribute("message", "Логин занят, попробуйте использовать другой");
        }
        return "register";
    }

    @GetMapping("/logout")
    public String handlerLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

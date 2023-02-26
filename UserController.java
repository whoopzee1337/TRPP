package ru.mtsbank.malikov.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.evsmanko.mankoff.entity.UserInfoEntity;
import ru.evsmanko.mankoff.service.UserInfoService;

@Controller
@AllArgsConstructor
@RequestMapping("/malikov")
public class UserController {
    private final UserInfoService userInfoService;

    @GetMapping("/user/{id}")
    public String showUserInfo(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userInfoService.findUserById(id));
        return "UserInfo";
    }

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userInfoService.findAll());
        return "Users";
    }

    @GetMapping("/user/create")
    public String showNewUserForm(Model model) {
        model.addAttribute("user", new UserInfoEntity());
        return "NewUserForm";
    }

    @PostMapping
    public String createNewUser(@ModelAttribute("user") @Valid UserInfoEntity user,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "NewUserForm";
        userInfoService.save(user);
        return "redirect:/malikov/users";
    }
}

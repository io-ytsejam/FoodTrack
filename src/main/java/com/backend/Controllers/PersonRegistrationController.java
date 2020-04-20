package com.backend.Controllers;

import com.backend.Dto.UserRegistrationDto;
import com.backend.Models.PersonEntity;
import com.backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class PersonRegistrationController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto, BindingResult result) {

        PersonEntity existing = userService.findByNickname(userDto.getNickname());
        if (existing != null) {
            result.rejectValue("nickname", null, "There is already an account registered with that username");
        }
        if (result.hasErrors()) {
            return "registration";
        }
        userService.save(userDto);
        return "redirect:/registration?success";
    }
}


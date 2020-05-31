package com.backend.Controllers;

import com.backend.Dto.UserRegistrationDto;
import com.backend.Models.AuthToken;
import com.backend.Models.PersonEntity;
import com.backend.Security.JwtTokenUtil;
import com.backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/registration")
public class PersonRegistrationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

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

    @ResponseBody
    @PostMapping
    public ResponseEntity registerUserAccount(@RequestBody @Valid UserRegistrationDto userDto, BindingResult result) {

        PersonEntity existing = userService.findByNickname(userDto.getNickname());
        if (existing != null) {
            result.rejectValue("nickname", null, "There is already an account registered with that username");
        }
        if (result.hasErrors()) {
            //return "redirect:/api/registration";
            return new ResponseEntity<String>(result.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
        }
        userService.save(userDto);
        //return "redirect:/api/registration?success";
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getNickname(),
                        userDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final PersonEntity user = userService.findByNickname(userDto.getNickname());
        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new AuthToken(token,user.getNickname()));
    }
}


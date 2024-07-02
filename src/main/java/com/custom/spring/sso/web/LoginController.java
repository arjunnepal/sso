package com.custom.spring.sso.web;

import com.custom.spring.sso.client_registration.CustomClientRegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private final CustomClientRegistrationService customClientRegistrationService;

    public LoginController(CustomClientRegistrationService customClientRegistrationService) {
        this.customClientRegistrationService = customClientRegistrationService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("federationIDPs", this.customClientRegistrationService.getAllMini());
        return "login";
    }

}

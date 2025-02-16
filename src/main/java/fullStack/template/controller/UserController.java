package fullStack.template.controller;

import fullStack.template.models.UserApp;
import fullStack.template.service.UserAppService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

        @Autowired
        UserAppService userAppService;

        @GetMapping("hey")
        public String hey()
        {
            return "hey non protected";}

        @PostMapping("/auth/login")
        public String login(@RequestBody UserApp user) {
            return userAppService.verify(user.getEmail(), user.getPassword());
        }


        @PostMapping("/auth/register")
        public String register(@Valid @RequestBody UserApp user)
        {
            return userAppService.register(user);
        }

        @GetMapping("/auth/hey")
        public String heyyy()
        { return "hey from auth il ya un erreur ";}

    }



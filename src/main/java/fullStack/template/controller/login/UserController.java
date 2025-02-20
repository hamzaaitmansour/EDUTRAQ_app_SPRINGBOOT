package fullStack.template.controller.login;

import fullStack.template.models.UserApp;
import fullStack.template.service.UserAppService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> login(@RequestBody UserApp user) {
        String token = userAppService.verify(user.getEmail(), user.getPassword());
        if ("Fail".equals(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Identifiants incorrects"));
        }
        return ResponseEntity.ok(Map.of("token", token)); // 🔥 Renvoie un objet JSON { token: "..." }
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



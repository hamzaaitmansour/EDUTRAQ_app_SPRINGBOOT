package fullStack.template.controller.login;

import fullStack.template.exception.EntityNotFoundException;
import fullStack.template.models.UserApp;
import fullStack.template.repository.UserAppRepo;
import fullStack.template.service.EmailService;
import fullStack.template.service.UserAppService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

        @Autowired
        UserAppService userAppService;
    @Autowired
    private UserAppRepo userAppRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @GetMapping("hey")
        public String hey()
        {
            return "hey non protected";}

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody UserApp user) {
        String token = userAppService.verify(user.getEmail(), user.getPassword());
        if ("Fail".equals(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Password incorrect"));
        }

        if ("Email".equals(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Email incorrect"));
        }
        return ResponseEntity.ok(Map.of("token", token)); // 🔥 Renvoie un objet JSON { token: "..." }
    }

    @GetMapping("/auth/forgotpassword/{email}")
    public ResponseEntity<?> forgotPassword(@PathVariable("email") String email) {
        userAppService.forgotPassword(email);
        return ResponseEntity.ok().build();
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



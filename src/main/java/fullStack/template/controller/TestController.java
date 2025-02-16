package fullStack.template.controller;

import fullStack.template.models.Etudiant;
import fullStack.template.repository.EtudiantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private EtudiantRepo repo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @GetMapping("")
    public String g()
    {
        return "test fonctionne";
    }
    @PostMapping("/etudiant")
    public Etudiant add(@RequestBody Etudiant etudiant)
    {
        System.out.println(etudiant.getFirstname());
        etudiant.setPassword(encoder.encode(etudiant.getPassword()));
        return repo.save(etudiant);
    }
}

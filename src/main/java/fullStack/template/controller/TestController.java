package fullStack.template.controller;

import fullStack.template.entities.Feedback;
import fullStack.template.models.Etudiant;
import fullStack.template.repository.EtudiantRepo;
import fullStack.template.repository.FeedbackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Etudiant> add(@RequestBody Etudiant etudiant)
    {
        etudiant.setPassword(encoder.encode(etudiant.getPassword()));
        return new ResponseEntity<>(repo.save(etudiant), HttpStatus.CREATED);

    }

}

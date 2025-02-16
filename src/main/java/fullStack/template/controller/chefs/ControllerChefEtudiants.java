package fullStack.template.controller.chefs;

import fullStack.template.models.Etudiant;
import fullStack.template.service.EtudiantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chef/etudiant")
public class ControllerChefEtudiants {
@Autowired
    private EtudiantService etudiantService;
@PostMapping()
    public ResponseEntity<Etudiant> add(@Valid @RequestBody Etudiant etudiant)
    {
     return new ResponseEntity<>(etudiantService.addEtudiant(etudiant), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Etudiant>> getAll()
    {
        return new ResponseEntity<>(etudiantService.getAllEtudiants(),HttpStatus.OK);
    }

    @GetMapping("/filiere/{id}")
    public ResponseEntity<List<Etudiant>> getAllByFiliere(@PathVariable Long id)
    {
        return new ResponseEntity<>(etudiantService.getAllEtudiantsByFiliere(id),HttpStatus.OK);
    }
    @PutMapping()
    public ResponseEntity<Etudiant> update(@Valid @RequestBody Etudiant etudiant)
    {
        return new ResponseEntity<>(etudiantService.updateEtudiant(etudiant), HttpStatus.ACCEPTED);
    }


    @GetMapping("/{email}")
    public ResponseEntity<Etudiant> getByEmail(@PathVariable String email)
    {
        return new ResponseEntity<>(etudiantService.getEtudiant(email),HttpStatus.OK);
    }
}

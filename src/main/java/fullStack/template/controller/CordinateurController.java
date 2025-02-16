package fullStack.template.controller;

import fullStack.template.entities.Filiere;
import fullStack.template.entities.Matiere;
import fullStack.template.entities.Salle;
import fullStack.template.service.FiliereService;
import fullStack.template.service.MatiereService;
import fullStack.template.service.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 // Autoriser les requêtes depuis d'autres domaines
@RestController
@RequestMapping("/api/cordinateur")
public class CordinateurController {

    @Autowired
    private FiliereService filiereService;

    @Autowired
    private MatiereService matiereService;
    @Autowired
    private SalleService salleService;

    // FILIERE
    @PostMapping("/filiere")
    public ResponseEntity<Filiere> addFiliere(@RequestBody Filiere filiere) {
        return new ResponseEntity<>(filiereService.addFiliere(filiere), HttpStatus.CREATED);
    }

    @DeleteMapping("/filiere/{id}")
    public ResponseEntity<?> deleteFiliere(@PathVariable Long id) {
        filiereService.deleteFiliere(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filiere")
    public ResponseEntity<List<Filiere>> findAll() {
        return new ResponseEntity<>(filiereService.findAll(), HttpStatus.OK);
    }

    // MATIERE
    @PostMapping("/matiere")
    public ResponseEntity<Matiere> addMatiere(@RequestBody Matiere matiere) {
        System.out.println(matiere.getNom());
        return new ResponseEntity<>(matiereService.addMatiere(matiere), HttpStatus.CREATED);
    }

    @DeleteMapping("/matiere/{nom}")
    public ResponseEntity<?> deleteMatiere(@PathVariable String nom) throws Exception {
        matiereService.deleteMatiere(nom);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/matiere")
    public ResponseEntity<List<Matiere>> findAllMatieres() {
        return new ResponseEntity<>(matiereService.findAll(), HttpStatus.OK);
    }

    // RACINE
    @GetMapping("")
    public String g() {
        return "cordinateur";
    }




    //SAlles
     @PostMapping("/salle")
     public ResponseEntity<Salle> addSalle(@RequestBody Salle salle)
     {
         return new ResponseEntity<>(salleService.addSalle(salle),HttpStatus.CREATED);
     }
     @DeleteMapping("/salle/{id}")
     public ResponseEntity<?> addSalle(@PathVariable Long id)
     {  salleService.deleteSalle(id);
         return  ResponseEntity.noContent().build();
     }

     @GetMapping("/salle")
     public ResponseEntity<List<Salle>> getAllSalles()
     {
         return new ResponseEntity<>(salleService.getall(),HttpStatus.OK);
     }
}

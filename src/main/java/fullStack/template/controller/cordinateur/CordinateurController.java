package fullStack.template.controller.cordinateur;

import fullStack.template.dto.ChefFiliereRequest;
import fullStack.template.entities.Filiere;
import fullStack.template.entities.Matiere;
import fullStack.template.entities.Salle;
import fullStack.template.models.ChefFiliere;
import fullStack.template.models.Professeur;
import fullStack.template.service.*;
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

    @Autowired
    private ProfService profService;

    @Autowired
    private ChefService chefService;

    // FILIERE
    @PostMapping("/filiere")
    public ResponseEntity<Filiere> addFiliere(@RequestBody Filiere filiere) {
        return new ResponseEntity<>(filiereService.updateFiliere(filiere), HttpStatus.CREATED);
    }
     @PutMapping("/filiere")
     public ResponseEntity<Filiere> updateFiliere(@RequestBody Filiere filiere) {
         return new ResponseEntity<>(filiereService.addFiliere(filiere), HttpStatus.ACCEPTED);
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


     // Profs
     @PostMapping("/prof")
     public ResponseEntity<Professeur> addProf(@RequestBody Professeur professeur) {
         return new ResponseEntity<>(profService.register(professeur), HttpStatus.CREATED);
     }
     @PutMapping("/prof")
     public ResponseEntity<Professeur> updateProf(@RequestBody Professeur professeur) {
         return new ResponseEntity<>(profService.update(professeur), HttpStatus.ACCEPTED);
     }

     @DeleteMapping("/prof/{id}")
     public ResponseEntity<?> deleteProf(@PathVariable Long id) {
         profService.deleteProf(id);

         return ResponseEntity.noContent().build();
     }

     @GetMapping("/prof/{email}")
     public ResponseEntity<Professeur> findProf(@PathVariable String email) {
         return new ResponseEntity<>(profService.findByEmail(email), HttpStatus.OK);
     }
     @GetMapping("/prof")
     public ResponseEntity<List<Professeur>> findProfAll() {
         return new ResponseEntity<>(profService.findAll(), HttpStatus.OK);
     }

     // CHEF
     @PostMapping("/chef")
     public ResponseEntity<ChefFiliere> addChef(@RequestBody ChefFiliereRequest chef) {
         return new ResponseEntity<>(chefService.addChef(chef), HttpStatus.CREATED);
     }
     @PutMapping("/chef")
     public ResponseEntity<ChefFiliere> updateChef(@RequestBody ChefFiliere chef) {
         return new ResponseEntity<>(chefService.update(chef), HttpStatus.ACCEPTED);
     }

     @DeleteMapping("/chef/{id}")
     public ResponseEntity<?> deleteChef(@PathVariable Long id) {
         chefService.delete(id);
         return ResponseEntity.noContent().build();
     }

     @GetMapping("/chef/{email}")
     public ResponseEntity<ChefFiliere> findChef(@PathVariable String email) {
         return new ResponseEntity<>(chefService.findByEmail(email), HttpStatus.OK);
     }
     @GetMapping("/chef")
     public ResponseEntity<List<ChefFiliere>> findChefsAll() {
         return new ResponseEntity<>(chefService.getAll(), HttpStatus.OK);
     }
}

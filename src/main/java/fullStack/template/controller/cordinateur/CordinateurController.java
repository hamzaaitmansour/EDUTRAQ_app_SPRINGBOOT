package fullStack.template.controller.cordinateur;

import fullStack.template.dto.*;
import fullStack.template.entities.Filiere;
import fullStack.template.entities.Matiere;
import fullStack.template.entities.Salle;
import fullStack.template.models.Professeur;
import fullStack.template.service.*;
import jakarta.validation.Valid;
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
     @Autowired
     private UserAppService userAppService;

     // FILIERE
    @PostMapping("/filiere")
    public ResponseEntity<Filiere> addFiliere(@RequestBody Filiere filiere) {

        return new ResponseEntity<>(filiereService.addFiliere(filiere), HttpStatus.CREATED);
    }
     @PutMapping("/filiere")
     public ResponseEntity<Filiere> updateFiliere(@RequestBody Filiere filiere) {
         return new ResponseEntity<>(filiereService.updateFiliere(filiere), HttpStatus.ACCEPTED);
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
     @PutMapping("/matiere")
     public ResponseEntity<Matiere> updateMatiere(@RequestBody Matiere matiere) {

         return new ResponseEntity<>(matiereService.updateMatiere(matiere), HttpStatus.ACCEPTED);
     }

    @DeleteMapping("/matiere/{id}")
    public ResponseEntity<?> deleteMatiere(@PathVariable Long id) throws Exception {
        matiereService.deleteMatiere(id);
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
     public ResponseEntity<Professeur> addProf(@Valid @RequestBody ProfRequestCordinateur professeur) {
        Professeur f=new Professeur();
        f.setEmail(professeur.getEmail());
        f.setApogee(professeur.getApogee());
        f.setAccount_complete(false);
         return new ResponseEntity<>(profService.register(f), HttpStatus.CREATED);
     }
     @PutMapping("/prof")
     public ResponseEntity<Professeur> updateProf(@Valid @RequestBody ProfRequestCordinateur professeur) {
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
     public ResponseEntity<List<ProfRequestCordinateur>> findProfAll() {
         return new ResponseEntity<>(profService.findAll(), HttpStatus.OK);
     }

     // CHEF
     @PostMapping("/chef")
     public ResponseEntity<Professeur> addChef(@RequestBody ChefFiliereRequest chef) {
         return new ResponseEntity<>(chefService.addChef(chef), HttpStatus.CREATED);
     }

     @GetMapping("/prof/chef")
     public ResponseEntity<List<ProfSimple>> findProfChefAll() {
        return new ResponseEntity<>(profService.getSimpleProfs(),HttpStatus.OK);
     }
     @GetMapping("/filiere/chef")
     public ResponseEntity<List<FiliereSimple>> findProfFiliereChefAll() {
        return  new ResponseEntity<>(filiereService.getSimples(),HttpStatus.OK);
     }
     @GetMapping("/chef")
     public ResponseEntity<List<ChefResponse>> getAllChefs()
     {
         System.out.println(" Get ALL ");
         return new ResponseEntity<>(profService.getAllChefs(),HttpStatus.OK);
     }
     @DeleteMapping("/chef/{id}")
     public  void delete(@PathVariable Long id)
     {
         profService.deleteChef(id);
     }

     @GetMapping("/profile/{id}")
     public ResponseEntity<CordinateurResponseProfile> getProfile(@PathVariable Long id)
     {
         return new ResponseEntity<>(userAppService.getProfile(id),HttpStatus.OK);
     }
     @PutMapping("/profile/{id}")
     public ResponseEntity<?> updateProfile(@PathVariable Long id, @RequestBody CordinateurResponseProfile profile) {
         System.out.println("\n \n \nMethode Post Fonctionne ");
        userAppService.updateProfile(id,profile);

        return ResponseEntity.accepted().build();

     }

}

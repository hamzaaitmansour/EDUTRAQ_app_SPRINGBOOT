package fullStack.template.controller.chefs;

import fullStack.template.dto.Image;
import fullStack.template.dto.UpdateEtudiant;
import fullStack.template.models.Etudiant;
import fullStack.template.service.EtudiantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/chef/etudiant")
public class ControllerChefEtudiants {
@Autowired
    private EtudiantService etudiantService;
    @PostMapping("/{id}")
    public ResponseEntity<String> add(@PathVariable Long id, @RequestBody Image etudiant)
    {
        System.out.println("\n\n hey hey \n\n");
        System.out.println(etudiant.toString());
     return new ResponseEntity<>(etudiantService.addEtudiant(etudiant,id), HttpStatus.CREATED);
    }
   @DeleteMapping("/{id}")
   public ResponseEntity<?> delete(@PathVariable Long id)
   {
       etudiantService.deleteEtudiant(id);
       return ResponseEntity.noContent().build();
   }
    @GetMapping("/etudiant/{id}")
    public ResponseEntity<Image> getEtudiant(@PathVariable Long id) {
        // Récupérer l'entité ImageEntity à partir de l'ID
        Etudiant imageEntity = etudiantService.getEtudiantById(id);

        if (imageEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }


        Image response= new Image();

        response.setEmail(imageEntity.getEmail());

        response.setPassword(imageEntity.getPassword());

        response.setProfile("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageEntity.getProfile()));

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEtudiant(@RequestBody UpdateEtudiant etudiant,@PathVariable Long id)
    {
         etudiantService.updateEtudiantFromChef(etudiant,id);
         return ResponseEntity.accepted().build();
    }
    @GetMapping()
    public ResponseEntity<List<Etudiant>> getAll()
    {
        return new ResponseEntity<>(etudiantService.getAllEtudiants(),HttpStatus.OK);
    }

    @GetMapping("/filiere/{id}")
    public ResponseEntity<List<Image>> getAllByFiliere(@PathVariable Long id)
    {
        System.out.println("Requete pour get etudiants ");
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

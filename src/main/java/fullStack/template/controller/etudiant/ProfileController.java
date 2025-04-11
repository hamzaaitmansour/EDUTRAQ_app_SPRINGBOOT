package fullStack.template.controller.etudiant;

import fullStack.template.dto.EtudiantProfileWeb;
import fullStack.template.dto.Image;
import fullStack.template.models.Etudiant;
import fullStack.template.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/api/etudiant/profileweb")
public class ProfileController {
    @Autowired
    private EtudiantService etudiantService;

    @GetMapping("/{id}")
    public ResponseEntity<Image> getEtudiant(@PathVariable Long id) {
        System.out.println("\n\n getEtudiant profile web");
        Etudiant imageEntity = etudiantService.getEtudiantById(id);
        if (imageEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Image response= new Image();
        response.setEmail(imageEntity.getEmail());
       // response.setPassword(imageEntity.getPassword());
        if(imageEntity.getProfile() != null)
         response.setProfile("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageEntity.getProfile()));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PutMapping("")
    public ResponseEntity<?> updateProfile(@RequestBody EtudiantProfileWeb profile)
    {
        System.out.println("\n\n update profile web, , "+profile.getProfile());
         etudiantService.updateProfileWeb(profile);
          return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}

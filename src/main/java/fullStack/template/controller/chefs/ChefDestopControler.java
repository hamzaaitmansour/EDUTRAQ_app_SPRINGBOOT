package fullStack.template.controller.chefs;

import fullStack.template.dto.desktop.FingerPrint;
import fullStack.template.dto.desktop.PresenceDeskRequest;
import fullStack.template.dto.desktop.ProfileChef;
import fullStack.template.entities.Presence;
import fullStack.template.models.Etudiant;
import fullStack.template.service.EtudiantService;
import fullStack.template.service.PresenceServie;
import fullStack.template.service.ProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/desk/chef")
public class ChefDestopControler {

    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private PresenceServie presenceServie;
    @Autowired
    private ProfService profService;

    @GetMapping("/etudiant/{id}")
    public ResponseEntity<List<Etudiant>> getAll(@RequestBody Long id )
    {
        return new ResponseEntity<>(etudiantService.getsByChef(id), HttpStatus.OK);
    }

    @PostMapping("/etudiant")
    public ResponseEntity<?> saveFingerpreinte(@RequestBody FingerPrint fingerPrint)
    {
        System.out.println(" \n\n Api Api\n\n");
        System.out.println(fingerPrint.toString());
        etudiantService.saveFinger(fingerPrint);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/presence/{id}")
    public ResponseEntity<?> addALlPresences(@PathVariable Long id , @RequestBody List<PresenceDeskRequest> presences)
    {
        System.out.println(" \n\n Api Api\n\n");
        System.out.println(presences.toString());
        System.out.println(presences.size());
        presenceServie.addAllPresences(id,presences);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody ProfileChef profile)
    {
          System.out.println(" \n\n Api Api Update Chef DESK\n\n");
          profService.updateProfileDesktop(profile);
          return ResponseEntity.accepted().build();
    }



}

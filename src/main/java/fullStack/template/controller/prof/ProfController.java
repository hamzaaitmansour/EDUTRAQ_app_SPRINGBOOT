package fullStack.template.controller.prof;

import fullStack.template.dto.PresenceRequest;
import fullStack.template.entities.Presence;
import fullStack.template.service.EtudiantService;
import fullStack.template.service.PresenceServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prof")
public class ProfController {
    @Autowired
    private PresenceServie presenceServie;
    @Autowired
    private EtudiantService etudiantService;
    // add presence sur une seance pour un etudiant

    @PostMapping("/presence")
    public ResponseEntity<Presence> addPresence(@RequestBody PresenceRequest presenceRequest) {
        return new ResponseEntity<>(presenceServie.addP(presenceRequest), HttpStatus.CREATED);
    }

    @GetMapping("/presence/{empreinte}")
    public ResponseEntity<Boolean> GetEtudiant(@PathVariable String empreinte) {
        if (etudiantService.getByEmpreinte(empreinte) != null)
            return new ResponseEntity<>(true, HttpStatus.OK);
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
}
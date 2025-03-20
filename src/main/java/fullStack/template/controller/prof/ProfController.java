package fullStack.template.controller.prof;

import fullStack.template.dto.EtudiantResponsedesktop;
import fullStack.template.dto.PresenceRequest;
import fullStack.template.dto.SeanceResponse;
import fullStack.template.entities.Matiere;
import fullStack.template.entities.Presence;
import fullStack.template.models.Etudiant;
import fullStack.template.service.EtudiantService;
import fullStack.template.service.PresenceServie;
import fullStack.template.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/prof")
public class ProfController {
    @Autowired
    private PresenceServie presenceServie;
    @Autowired
    private SeanceService seanceService;
    @Autowired
    private EtudiantService etudiantService;
    // add presence sur une seance pour un etudiant

//    @PostMapping("/presence")
//    public ResponseEntity<Presence> addPresence(@RequestBody PresenceRequest presenceRequest) {
//        return new ResponseEntity<>(presenceServie.addP(presenceRequest), HttpStatus.CREATED);
//    }



    @GetMapping("/emploi/{id}")
    public ResponseEntity<List<SeanceResponse>> findAllSeancesByProf(@PathVariable  Long id)
    {
        return new ResponseEntity<>(seanceService.getSeancesByProf(id),HttpStatus.OK);
    }
    @GetMapping("/matieres/{id}")
    public ResponseEntity<List<Matiere>> findAllMatieresByProf(@PathVariable  Long id)
    {
        return new ResponseEntity<>(seanceService.getMatieresByProf(id),HttpStatus.OK);
    }

    @GetMapping("/etudiants/{id}")
    public ResponseEntity<List<EtudiantResponsedesktop>> findAllEtudiantsBySeance(@PathVariable  Long id)
    {
        return new ResponseEntity<>(etudiantService.getAllByBySeance(id),HttpStatus.OK);
    }
    @PostMapping("/presence")
    public ResponseEntity<?> addpresences(@RequestBody List<PresenceRequest> prs)
    {
        presenceServie.addAll(prs);
        return  ResponseEntity.ok().build();
    }

    // 0059

}
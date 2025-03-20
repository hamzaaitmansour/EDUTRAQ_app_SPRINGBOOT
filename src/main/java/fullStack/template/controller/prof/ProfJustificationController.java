package fullStack.template.controller.prof;

import fullStack.template.dto.desktop.JustRequestD;
import fullStack.template.dto.desktop.JustResponseD;
import fullStack.template.entities.Filiere;
import fullStack.template.entities.JustificationAbsence;
import fullStack.template.entities.Matiere;
import fullStack.template.models.Professeur;
import fullStack.template.repository.FiliereRepo;
import fullStack.template.repository.ProfRepo;
import fullStack.template.service.FiliereService;
import fullStack.template.service.JustificationService;
import fullStack.template.service.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/prof/justification")
public class ProfJustificationController {
    @Autowired
   private JustificationService justificationService;
    @Autowired
    private ProfRepo profRepo;

    @Autowired
    private MatiereService matiereService;
    @Autowired
    private FiliereService filiereService;


    @PostMapping("")
    public ResponseEntity<List<JustResponseD>> getJustification(@RequestBody JustRequestD j)
    {
        System.out.println("Hryy");
        return new ResponseEntity<>(justificationService.getAllForProfFiliereMatiere(j), HttpStatus.OK);
    }
    @GetMapping("/last/{id}")
    public  ResponseEntity<List<JustResponseD>> getLastJusts(@PathVariable Long id)
    {
        return new ResponseEntity<>(justificationService.getLastJustificationFiliere(id),HttpStatus.OK);
    }


    @GetMapping("/matiere/{id}")
    public ResponseEntity<List<Matiere>> getMatieres(@PathVariable  Long id)
    {
        Professeur prof = profRepo.findById(id).orElseThrow();
        return new ResponseEntity<>(matiereService.getMatiereByProf(prof),HttpStatus.OK);
    }
    @GetMapping("/filiere/{id}")
    public ResponseEntity<List<Filiere>> getFilieres(@PathVariable  Long id)
    {
        Professeur prof=profRepo.findById(id).orElseThrow();
        return new ResponseEntity<>(filiereService.getFilieresByProf(prof),HttpStatus.OK);
    }


}
